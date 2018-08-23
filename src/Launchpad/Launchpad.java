/**
 * Created by William Davis on 17/08/2018.
 */
package Launchpad;

import Launchpad.Input.InputReceiver;
import Launchpad.Output.CommandMessage;
import Launchpad.Output.DefaultMessage;

import javax.sound.midi.*;

public class Launchpad
{
    private boolean valid;
    private MidiDevice receiverDevice;
    private MidiDevice transmitterDevice;
    private Receiver receiver;
    private InputReceiver transmitterReceiver;
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
            Transmitter transmitter = this.transmitterDevice.getTransmitter();
            this.transmitterReceiver = new InputReceiver();
            transmitter.setReceiver(this.transmitterReceiver);
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

    public InputReceiver getTransmitter()
    {
        return this.transmitterReceiver;
    }

    public void sendMessage(DefaultMessage message) throws InvalidMidiDataException
    {
        if(this.valid)
        {
            this.receiver.send(message.getMessage(), -1);
        }
    }
}
