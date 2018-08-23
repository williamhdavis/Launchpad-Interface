/**
 * Created by William Davis on 22/08/2018.
 */
package Launchpad.Output;

import javax.sound.midi.MidiMessage;

public abstract class DefaultMessage
{
    /**
     * The GRID_SIZE class variable is used to define the width and height of the square button grid on the launchpad.
     */
    protected static final int GRID_SIZE = 8;

    /**
     * The generateBytes instance method is used to convert the stored data into a suitable byte array for use in a midi message.
     * @return - A byte array formatted version of the stored data.
     */
    protected abstract byte[] generateBytes();

    /**
     * The getMessage instance method is used to retrieve a new raw formatted midi message with suitable data embedded.
     * @return - The generated midi message.
     */
    public MidiMessage getMessage()
    {
        return new MidiRawMessage(this.generateBytes());
    }
}
