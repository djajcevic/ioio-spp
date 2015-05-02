package hr.djajcevic.spc.hardware.ui;

import android.app.Activity;
import android.widget.Toast;

import hr.djajcevic.spc.hardware.Encoder;
import hr.djajcevic.spc.hardware.Motor;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;

/**
 * Created by djajcevic on 02.05.15..
 */
public class SolarPanelPositionController extends BaseIOIOLooper {

    public static final int POSITION_TOLERANCE = 10;
    protected Encoder horizontalPanelPosition;
    protected Encoder verticalPanelPosition;

    protected Motor horizontalMotor;
    protected Motor verticalMotor;

    private int targetAzimuth;
    private int targetLatitude;

    public static enum CurrentProcess {
        STANDBY, HORIZONTAL_POSITIONING, VERTICAL_POSITIONING
    }

    public static enum Status {
        INITIALIZING, OK, H_MOTOR_ERROR, V_MOTOR_ERROR, H_SENSOR_ERROR, V_SENSOR_ERROR
    }

    private CurrentProcess currentProcess = CurrentProcess.STANDBY;
    private Status status = Status.INITIALIZING;

    private Activity activity;

    public SolarPanelPositionController(Activity activity) {
        this.activity = activity;
        horizontalPanelPosition = new Encoder(0, 5.0, 5, 6, 7, POSITION_TOLERANCE, 11, 12, 13, 14);
        verticalPanelPosition = new Encoder(0, 5.0, 18, 19, 20, 21, 22, 23, 24, 25);

        horizontalMotor = new Motor(1, 2);
        verticalMotor = new Motor(3, 4);
    }

    @Override
    protected void setup() throws ConnectionLostException, InterruptedException {
        super.setup();
        showVersions(ioio_, "IOIO connected!");
        status = Status.INITIALIZING;
        horizontalPanelPosition.setup(ioio_);
        verticalPanelPosition.setup(ioio_);

        horizontalMotor.setup(ioio_);
        verticalMotor.setup(ioio_);

        try {
            horizontalPanelPosition.readCurrentValue();
        } catch (ConnectionLostException e) {
            status = Status.H_SENSOR_ERROR;
            // TODO: publish sensor status
        }

        try {
            verticalPanelPosition.readCurrentValue();
        } catch (ConnectionLostException e) {
            status = Status.V_SENSOR_ERROR;
            // TODO: publish sensor status
        }
    }

    @Override
    public void loop() throws ConnectionLostException, InterruptedException {

        movePanel();

        Thread.sleep(500);
    }

    private void movePanel() throws ConnectionLostException, InterruptedException {
        if (currentProcess == CurrentProcess.STANDBY) {
            currentProcess = CurrentProcess.HORIZONTAL_POSITIONING;
        }

        if (currentProcess == CurrentProcess.HORIZONTAL_POSITIONING) {
            horizontalPanelPosition.readCurrentValue();
            int hDegrees = horizontalPanelPosition.getDegrees();

            int diff = targetAzimuth - hDegrees;

            if (diff > POSITION_TOLERANCE) {
                horizontalMotor.rotateClockwise(false);
            } else if (diff < POSITION_TOLERANCE) {
                horizontalMotor.rotateClockwise(true);
            } else if (diff < POSITION_TOLERANCE && diff > -POSITION_TOLERANCE) {
                horizontalMotor.stop();
                currentProcess = CurrentProcess.STANDBY;
            }

        } else if (currentProcess == CurrentProcess.VERTICAL_POSITIONING) {
            verticalPanelPosition.readCurrentValue();
            int vDegrees = verticalPanelPosition.getDegrees();

            int diff = targetLatitude - vDegrees;

            if (diff > POSITION_TOLERANCE) {
                verticalMotor.rotateClockwise(false);
            } else if (diff < POSITION_TOLERANCE) {
                verticalMotor.rotateClockwise(true);
            } else if (diff < POSITION_TOLERANCE && diff > -POSITION_TOLERANCE) {
                verticalMotor.stop();
                currentProcess = CurrentProcess.STANDBY;
            }
        }
    }

    public int getTargetAzimuth() {
        return targetAzimuth;
    }

    public void setTargetAzimuth(int targetAzimuth) {
        this.targetAzimuth = targetAzimuth;
    }

    public int getTargetLatitude() {
        return targetLatitude;
    }

    public void setTargetLatitude(int targetLatitude) {
        this.targetLatitude = targetLatitude;
    }

    @Override
    public void disconnected() {
        horizontalPanelPosition.disconect();
        verticalPanelPosition.disconect();

        horizontalMotor.disconect();
        verticalMotor.disconect();

        toast("IOIO disconected");
    }

    @Override
    public void incompatible() {
        showVersions(ioio_, "Incompatible firmware version!");
    }

    private void showVersions(IOIO ioio, String title) {
        toast(String.format("%s\n" +
                        "IOIOLib: %s\n" +
                        "Application firmware: %s\n" +
                        "Bootloader firmware: %s\n" +
                        "Hardware: %s",
                title,
                ioio.getImplVersion(IOIO.VersionType.IOIOLIB_VER),
                ioio.getImplVersion(IOIO.VersionType.APP_FIRMWARE_VER),
                ioio.getImplVersion(IOIO.VersionType.BOOTLOADER_VER),
                ioio.getImplVersion(IOIO.VersionType.HARDWARE_VER)));
    }

    private void toast(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
