package hr.djajcevic.spc.hardware;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;

/**
 * This is the thread on which all the IOIO activity happens. It will be run
 * every time the application is resumed and aborted when it is paused. The
 * method setup() will be called right after a connection with the IOIO has
 * been established (which might happen several times!). Then, loop() will
 * be called repetitively until the IOIO gets disconnected.
 *
 * Created by djajcevic on 29.05.15..
 */
public class CustomLooper extends BaseIOIOLooper {
    /** The on-board LED. */
    private DigitalOutput led_;
    private LooperDelegate delegate;

    public CustomLooper(LooperDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Called every time a connection with IOIO has been established.
     * Typically used to open pins.
     *
     * @throws ioio.lib.api.exception.ConnectionLostException
     *             When IOIO connection is lost.
     *
    //         * @see ioio.lib.util.IOIOLooper#setup()
     */
    @Override
    protected void setup() throws ConnectionLostException {
        delegate.showMessage("\n" + showVersions(ioio_, "IOIO connected!"));

        led_ = ioio_.openDigitalOutput(IOIO.LED_PIN, true);

        delegate.enableUi(true);
    }

    /**
     * Called repetitively while the IOIO is connected.
     *
     * @throws ioio.lib.api.exception.ConnectionLostException
     *             When IOIO connection is lost.
     * @throws InterruptedException
     * 				When the IOIO thread has been interrupted.
     *
     * @see ioio.lib.util.IOIOLooper#loop()
     */
    @Override
    public void loop() throws ConnectionLostException, InterruptedException {
        led_.write(true);
        delegate.loop(this);
        led_.write(false);
    }

    /**
     * Called when the IOIO is disconnected.
     *
     * @see ioio.lib.util.IOIOLooper#disconnected()
     */
    @Override
    public void disconnected() {
        delegate.enableUi(false);
        delegate.showMessage("\nIOIO disconnected\n");
    }

    /**
     * Called when the IOIO is connected, but has an incompatible firmware version.
     *
     * @see ioio.lib.util.IOIOLooper#incompatible(ioio.lib.api.IOIO)
     */
    @Override
    public void incompatible() {
        delegate.showMessage("\n" + showVersions(ioio_, "Incompatible firmware version!"));
    }

    private String showVersions(IOIO ioio, String title) {
        return String.format("%s\n" +
                        "IOIOLib: %s\n" +
                        "Application firmware: %s\n" +
                        "Bootloader firmware: %s\n" +
                        "Hardware: %s",
                title,
                ioio.getImplVersion(IOIO.VersionType.IOIOLIB_VER),
                ioio.getImplVersion(IOIO.VersionType.APP_FIRMWARE_VER),
                ioio.getImplVersion(IOIO.VersionType.BOOTLOADER_VER),
                ioio.getImplVersion(IOIO.VersionType.HARDWARE_VER));
    }
}
