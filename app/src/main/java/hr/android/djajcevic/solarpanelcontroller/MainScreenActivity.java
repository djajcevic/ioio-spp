package hr.android.djajcevic.solarpanelcontroller;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hr.android.djajcevic.solarpanelcontroller.util.SystemUiHider;
import hr.djajcevic.spc.SolarPanelController;
import hr.djajcevic.spc.SolarPanelControllerImpl;
import hr.djajcevic.spc.calculator.SunPositionData;
import hr.djajcevic.spc.hardware.CompassDelegate;
import hr.djajcevic.spc.hardware.CustomLooper;
import hr.djajcevic.spc.hardware.LooperDelegate;
import hr.djajcevic.spc.hardware.Compass;
import hr.djajcevic.spc.ioio.looper.compas.CompassData;
import hr.djajcevic.spc.ioio.looper.exception.SystemException;
import hr.djajcevic.spc.ioio.looper.gps.GPSData;
import hr.djajcevic.spc.ioio.looper.process.SystemManager;
import hr.djajcevic.spc.ioio.looper.process.SystemManagerListener;
import hr.djajcevic.spc.util.Configuration;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see hr.android.djajcevic.solarpanelcontroller.util.SystemUiHider
 */
public class MainScreenActivity extends IOIOActivity implements LooperDelegate, SystemManagerListener, SystemManager.Delegate {

    private Compass mCompass;

    private TextView systemMessageTextView;
    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private TextView headingTextView;
    private TextView sunAzimuthTextView;
    private TextView horizontalAxisTextView;
    private TextView verticalAxisTextView;
    private LocationManager locationService;
    private TextView sunHeightTextView;
    private CustomLooper customLooper;
    private ScrollView systemMessageTextViewScrollView;

    private static DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy HH:MM:ss");

    SystemManager systemManager;
    private Switch sleepSwitch;
    private Switch systemCheckMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View contentView = findViewById(R.id.frameLayout);

        setContentView(R.layout.activity_main_screen);

        systemMessageTextView = (TextView) findViewById(R.id.systemMessageTextView);
        sunriseTextView = (TextView) findViewById(R.id.sunriseTextView);
        sunsetTextView = (TextView) findViewById(R.id.sunsetTextView);
        headingTextView = (TextView) findViewById(R.id.headingTextView);
        sunAzimuthTextView = (TextView) findViewById(R.id.sunAzimuthTextView);
        sunHeightTextView = (TextView) findViewById(R.id.sunHeightTextView);
        horizontalAxisTextView = (TextView) findViewById(R.id.horizontalAxisTextView);
        verticalAxisTextView = (TextView) findViewById(R.id.verticalAxisTextView);
        systemMessageTextViewScrollView = (ScrollView) findViewById(R.id.systemMessageTextViewScrollView);

        sleepSwitch = (Switch) findViewById(R.id.sleepSwitch);
        systemCheckMode = (Switch) findViewById(R.id.systemCheckMode);

        // sensors
        log("\n");

        log("Setup controller...\n");
        try {
            Configuration.initialize(getApplicationContext().getAssets().open("configuration.properties"), getApplicationContext().getAssets().open("status.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        systemManager = new SystemManager();
        systemManager.getListeners().add(this);
        systemManager.setDelegate(this);

    }

    /**
     * A method to create our IOIO thread.
     *
     * @see ioio.lib.util.AbstractIOIOActivity#createIOIOThread()
     */
    @Override
    protected IOIOLooper createIOIOLooper() {
        return systemManager;
    }

    private void toast(final String message) {
        final Context context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                log(message);
            }
        });
    }

    @Override
    public synchronized void showMessage(String message) {
        toast(message);
    }

    private int numConnected_ = 0;

    @Override
    public void enableUi(final boolean enable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (enable) {
                    if (numConnected_++ == 0) {
//                        button_.setEnabled(true);
                    }
                } else {
                    if (--numConnected_ == 0) {
//                        button_.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    public void loop(CustomLooper looper) throws ConnectionLostException, InterruptedException {
//        led_.write(!button_.isPressed());
//        redLedOne_.write(!button_.isPressed());
        systemManager.loop();
        Thread.sleep(10000);
    }

    @Override
    protected void onResume() {
        toast("Resuming...\n");
        super.onResume();
    }

    @Override
    protected void onPause() {
        toast("Pausing...\n");
        super.onPause();
    }

    public synchronized void log(final String message, final boolean flush) {
        if (message == null) {
            return;
        }
        System.out.println("MESSAGE: " + message);
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (flush) {
                    systemMessageTextView.setText("");
                }
                systemMessageTextView.append(dateFormat.format(new Date()) + ": \n" + message + "\n");
                systemMessageTextViewScrollView.post(new Runnable() {

                    @Override
                    public void run() {
                        systemMessageTextViewScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });
    }

    public synchronized void log(final String message) {
        log(message, false);
    }

    @Override
    public void boardConnected(IOIO ioio) {
        log("IOIO connected");

    }

    @Override
    public void boardDisconnected() {
        log("IOIO disconnected");
    }

    @Override
    public void incompatibleBoard(IOIO ioio) {
        log("IOIO incompatible");
    }

    @Override
    public void xAxisStepCompleted(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                horizontalAxisTextView.setText(i + "°");
            }
        });
    }

    @Override
    public void yAxisStepCompleted(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                verticalAxisTextView.setText(i + "°");
            }
        });
    }

    @Override
    public void xAxisReachedStartPosition() {
//        log("Reached start X position");
    }

    @Override
    public void yAxisReachedStartPosition() {
//        log("Reached start Y position");
    }

    @Override
    public void xAxisReachedEndPosition() {
//        log("Reached end X position");
    }

    @Override
    public void yAxisReachedEndPosition() {
//        log("Reached end Y position");
    }

    @Override
    public void gpsPositionLocked(GPSData gpsData) {
        final SunPositionData sunPositionData = systemManager.getSunPositionData();
        if (sunPositionData != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Calendar sunriseCalendar = sunPositionData.sunriseCalendar;
                    if (sunriseCalendar != null) {
                        sunriseTextView.setText(String.format("%02d:%02dh",sunriseCalendar.get(Calendar.HOUR_OF_DAY), sunriseCalendar.get(Calendar.MINUTE)));
                    }
                    Calendar sunsetCalendar = sunPositionData.sunsetCalendar;
                    if (sunsetCalendar != null) {
                        sunsetTextView.setText(String.format("%02d:%02dh", sunsetCalendar.get(Calendar.HOUR_OF_DAY), sunsetCalendar.get(Calendar.MINUTE)));
                    }
                    sunAzimuthTextView.setText((int) sunPositionData.azimuth + "°");
                    sunHeightTextView.setText((int) (90 - sunPositionData.zenith) + "°");
                }
            });
        }
    }

    @Override
    public void compassDataReady(final CompassData compassData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                headingTextView.setText((compassData.getHeadingDegrees().intValue()) + "°");
            }
        });
    }

    @Override
    public void systemError(Exception e) {
//        log(e.getMessage());
    }

    @Override
    public void performingParkDueTo(SystemException e) {
//        log(e.getMessage());
    }

    @Override
    public void message(String s) {
        log(s);
    }

    @Override
    public void beforeLoop(SystemManager systemManager) {
        log("Performing management", true);
        systemManager.setSleepOn(sleepSwitch.isChecked());
        systemManager.setSystemCheckModeOn(systemCheckMode.isChecked());
    }
}
