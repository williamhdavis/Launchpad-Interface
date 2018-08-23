/**
 * Created by William Davis on 22/08/2018.
 */
package Launchpad.Output;

import javax.sound.midi.MidiMessage;

public class MidiRawMessage extends MidiMessage
{
    /**
     * The MidiRawMessage constructor is used to create  a new raw midi message.
     * @param data - The bytes making up the message.
     */
    protected MidiRawMessage(byte[] data)
    {
        super(data);
    }

    @Override
    public Object clone()
    {
        return new MidiRawMessage(this.getMessage());
    }
}
