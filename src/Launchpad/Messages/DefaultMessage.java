/**
 * Created by William Davis on 22/08/2018.
 */
package Launchpad.Messages;

import javax.sound.midi.MidiMessage;

public abstract class DefaultMessage
{
    /**
     * The GRID_SIZE class variable is used to define the width and height of the square button grid on the launchpad.
     */
    protected static final int GRID_SIZE = 8;
    /**
     * The ROW_MULTIPLIER class variable is used to define the change in key number on the y-axis of the launchpad.
     */
    protected static final int ROW_MULTIPLIER = 16;

    /**
     * The CONTROLLER_MAIN class variable is used to define the controller for the menu keys and special functions.
     */
    protected static final byte CONTROLLER_MAIN = (byte)176;
    /**
     * The CONTROLLER_GRID class variable is used to define the controller to use for accessing the grid.
     */
    protected static final byte CONTROLLER_GRID = (byte)144;
    /**
     * The CONTROLLER_FULL class variable is used to define the controller for mass setting all key colours.
     */
    protected static final byte CONTROLLER_FULL = (byte)146;

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
