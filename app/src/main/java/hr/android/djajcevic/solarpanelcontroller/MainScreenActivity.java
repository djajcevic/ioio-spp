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
import android.widget.TextView;
import android.widget.Toast;

import hr.android.djajcevic.solarpanelcontroller.util.SystemUiHider;
import hr.djajcevic.spc.hardware.CompassDelegate;
import hr.djajcevic.spc.hardware.CustomLooper;
import hr.djajcevic.spc.hardware.LooperDelegate;
import hr.djajcevic.spc.hardware.Compass;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see hr.android.djajcevic.solarpanelcontroller.util.SystemUiHider
 */
public class MainScreenActivity extends IOIOActivity implements LooperDelegate, CompassDelegate, GpsStatus.Listener, LocationListener {

    private Compass mCompass;

    private TextView systemMessageTextView;
    private TextView sunAzimuthTextView;
    private LocationManager locationService;
    private TextView sunHeightTextView;
    private CustomLooper customLooper;
    private ScrollView systemMessageTextViewScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View contentView = findViewById(R.id.frameLayout);

        setContentView(R.layout.activity_main_screen);

        systemMessageTextView = (TextView) findViewById(R.id.systemMessageTextView);
        sunAzimuthTextView = (TextView) findViewById(R.id.sunAzimuthTextView);
        sunHeightTextView = (TextView) findViewById(R.id.sunHeightTextView);
        systemMessageTextViewScrollView = (ScrollView) findViewById(R.id.systemMessageTextViewScrollView);

        // sensors
        log("\n");
        log("Setup sensors...\n");
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mCompass = new Compass(getWindowManager(), sensorManager);
        mCompass.setDelegate(this);
        locationService = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationService.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        boolean b = locationService.addGpsStatusListener(this);
//        log("\nRegistered GPS listener? " + b);
        customLooper = new CustomLooper(this);
    }

    /**
     * A method to create our IOIO thread.
     *
     * @see ioio.lib.util.AbstractIOIOActivity#createIOIOThread()
     */
    @Override
    protected IOIOLooper createIOIOLooper() {
        return customLooper;
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
        looper.getLed().write(true);
        Thread.sleep(1000);
        looper.getLed().write(false);
        Thread.sleep(1000);
    }

    @Override
    protected void onResume() {
        toast("Resuming...\n");
        super.onResume();
        mCompass.onResume();
    }

    @Override
    protected void onPause() {
        toast("Pausing...\n");
        super.onPause();
        mCompass.onPause();
    }

    public void log(String message) {
        systemMessageTextView.append(message);
        systemMessageTextViewScrollView.post(new Runnable() {

            @Override
            public void run() {
                systemMessageTextViewScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void compassUpdated(Compass compass) {
        sunAzimuthTextView.setText(Math.round(compass.getAzimuth()) + "°");
    }

    @Override
    public void onGpsStatusChanged(int event) {
        GpsStatus gpsStatus = locationService.getGpsStatus(null);
    }

    @Override
    public void onLocationChanged(Location location) {
//        String gpsProvider = LocationManager.GPS_PROVIDER;
//        GpsStatus gpsStatus = locationService.getGpsStatus(null);

        sunHeightTextView.setText(location.getLongitude() + "° / "  + location.getLatitude() + "°");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
