package hr.djajcevic.spc.hardware;

import java.util.LinkedList;
import java.util.List;

import ioio.lib.api.DigitalInput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;

/**
 * Created by djajcevic on 02.05.15..
 */
public class Encoder {

    protected int position;
    protected double resolution;

    protected int[] ioioPinsIds;
    protected IOIO ioio;
    protected List<DigitalInput> ioioDigitalInputs;

    public Encoder(int position, double resolution, int... ioioPinsIds) {
        this.position = position;
        this.resolution = resolution;
        this.ioioPinsIds = ioioPinsIds;
        ioioDigitalInputs = new LinkedList<>();
    }

    public void setup(IOIO ioio) throws ConnectionLostException, InterruptedException {
        this.ioio = ioio;
        for (int i = 0; i < ioioPinsIds.length; i++) {
            int pin = ioioPinsIds[i];
            DigitalInput digitalInput = ioio.openDigitalInput(pin, DigitalInput.Spec.Mode.PULL_UP);
            ioioDigitalInputs.add(digitalInput);
        }
    }

    public void disconect() {
        for (DigitalInput ioioDigitalInput : ioioDigitalInputs) {
            ioioDigitalInput.close();
        }
    }

    public int readCurrentValue() throws ConnectionLostException, InterruptedException {
        StringBuilder binary = new StringBuilder();
        for (DigitalInput input : ioioDigitalInputs) {
            boolean pinValue = input.read();
            binary.insert(0, pinValue ? "1" : "0");
        }
        int currentPosition = Integer.parseInt(binary.toString(), 2);
        position = currentPosition;
        return currentPosition;
    }

    public int getDegrees() {
        return (int) (position * resolution);
    }

}
