package hr.djajcevic.spc.hardware;

import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

/**
 * Created by djajcevic on 02.05.15..
 */
public class Motor {

    int powerPin;
    int directionPin;

    protected IOIO ioio;
    protected DigitalOutput powerPinOutput;
    protected DigitalOutput directionPinOutput;

    protected boolean currentRotationClockwise;

    public Motor(int powerPin, int directionPin) {
        this.powerPin = powerPin;
        this.directionPin = directionPin;
    }

    public void setup(IOIO ioio) throws ConnectionLostException, InterruptedException {
        this.ioio = ioio;
        powerPinOutput = ioio.openDigitalOutput(powerPin, false);
        directionPinOutput = ioio.openDigitalOutput(directionPin, false);
    }

    public void disconect() {
        powerPinOutput.close();
        directionPinOutput.close();
    }

    public void rotateClockwise(boolean clockwise) throws ConnectionLostException {
        stop();

        currentRotationClockwise = clockwise;
        directionPinOutput.write(!currentRotationClockwise);

        start();
    }

    public void start() throws ConnectionLostException {
        powerPinOutput.write(true);
    }

    public void stop() throws ConnectionLostException {
        powerPinOutput.write(false);
    }


}
