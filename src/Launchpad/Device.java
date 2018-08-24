/**
 * Created by William Davis on 17/08/2018.
 */
package Launchpad;

import Launchpad.Input.InputReceiver;
import Launchpad.Output.Message;

import javax.sound.midi.*;

public class Device
{
    /**
     * The receiverDevice instance variable is used to store the launchpads midi receiver.
     */
    private MidiDevice receiverDevice;
    /**
     * The transmitterDevice instance variable is used to store the launchpads midi transmitter.
     */
    private MidiDevice transmitterDevice;
    /**
     * The receiver instance variable is used to store the code midi receiver.
     */
    private Receiver receiver;
    /**
     * The transmitterReceiver is used to store the handler for incoming messages from the launchpad.
     */
    private InputReceiver transmitterReceiver;

    /**
     * The Device constructor finds and prepares the launchpads input and output.
     * @throws MidiUnavailableException - Thrown if a midi device cannot be accessed.
     * @throws NullPointerException - Thrown if no Launchpad S is found.
     */
    public Device() throws MidiUnavailableException, NullPointerException
    {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        int i = 0;
        while(i < infos.length)
        {
            MidiDevice device = MidiSystem.getMidiDevice(infos[i]);
            if(infos[i].getName().equals("Launchpad S"))
            {
                if(device.getMaxReceivers() != 0 && this.receiverDevice == null)
                {
                    this.receiverDevice = device;
                }
                if(device.getMaxTransmitters() != 0 && this.transmitterDevice == null)
                {
                    this.transmitterDevice = device;
                }
            }
            ++i;
        }
        if(this.receiverDevice != null && this.transmitterDevice != null)
        {
            this.receiverDevice.open();
            this.receiver = this.receiverDevice.getReceiver();
            this.transmitterDevice.open();
            Transmitter transmitter = this.transmitterDevice.getTransmitter();
            this.transmitterReceiver = new InputReceiver();
            transmitter.setReceiver(this.transmitterReceiver);
            this.reset();
        }
        else
        {
            throw new NullPointerException("Launchpad S not found.");
        }
    }

    /**
     * The reset instance methof is used to set clear the launchpad of any set data.
     */
    private void reset()
    {
        try
        {
            this.sendMessage(new Message(Message.Command.RESET));
        }
        catch(InvalidMidiDataException ex)
        {}
    }

    /**
     * The close instance method is used to close the connection with the launchpad.
     */
    public void close()
    {
        this.reset();
        this.receiverDevice.close();
        this.transmitterDevice.close();
    }

    /**
     * The getTransmitter instance method is used to get access to the handler for incoming midi messages.
     * @return - The input handler.
     */
    public InputReceiver getTransmitter()
    {
        return this.transmitterReceiver;
    }

    /**
     * The sendMessage instance method is used to send a set of bytes as a midi message to the launchpad.
     * @param message - The data to send to the launchpad.
     * @throws InvalidMidiDataException - Thrown if the data is malformed.
     */
    public void sendMessage(Message message) throws InvalidMidiDataException
    {
        MidiMessage midiMessage = new MidiMessage(message.getData())
        {
            @Override
            public Object clone()
                {
                    return null;
                }
        };
        this.receiver.send(midiMessage, -1);
    }
}
