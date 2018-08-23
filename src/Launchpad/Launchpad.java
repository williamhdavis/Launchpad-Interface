/**
 * Created by William Davis on 17/08/2018.
 */
package Launchpad;

import Launchpad.Messages.CommandMessage;
import Launchpad.Messages.DefaultMessage;

import javax.sound.midi.*;

public class Launchpad
{
    private boolean valid;
    private MidiDevice receiverDevice;
    private MidiDevice transmitterDevice;
    private Receiver receiver;
    private Transmitter transmitter;
    private boolean flashEnabled;

    public Launchpad() throws MidiUnavailableException
    {
        this.valid = false;
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
            this.transmitter = this.transmitterDevice.getTransmitter();
            this.flashEnabled = false;
            this.valid = true;
        }
        else
        {
            throw new NullPointerException("Launchpad S not found.");
        }
    }

    public void close()
    {
        if(this.valid)
        {
            try
            {
                this.sendMessage(new CommandMessage(CommandMessage.Command.RESET));
            }
            catch(InvalidMidiDataException ex)
            {}
            this.receiverDevice.close();
            this.transmitterDevice.close();
        }
    }

    public void sendMessage(DefaultMessage message) throws InvalidMidiDataException
    {
        if(this.valid)
        {
            this.receiver.send(message.getMessage(), -1);
        }
    }
}
