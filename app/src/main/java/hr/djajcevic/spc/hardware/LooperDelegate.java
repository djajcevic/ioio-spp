package hr.djajcevic.spc.hardware;

import ioio.lib.api.exception.ConnectionLostException;

/**
 * Created by djajcevic on 29.05.15..
 */
public interface LooperDelegate {

    void showMessage(final String message);
    void enableUi(final boolean enable);
    void loop(final CustomLooper looper) throws ConnectionLostException, InterruptedException;

}
