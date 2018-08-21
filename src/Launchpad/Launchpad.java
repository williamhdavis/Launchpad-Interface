/**
 * Created by William Davis on 17/08/2018.
 */
package Launchpad;

import javax.sound.midi.*;

public class Launchpad
{
    /**
     * The GRID_SIZE class variable is used to define the width and height of the square button grid on the launchpad.
     */
    private static final int GRID_SIZE = 8;
    /**
     * The GRID_MULTIPLIER class variable is used to define the change in key number on the y-axis of the launchpad.
     */
    private static final int GRID_MULTIPLIER = 16;
    /**
     * The GRID_TOP_ROW_OFFSET class variable is used to define the offset from 0 of the top keys address.
     */
    private static final int GRID_TOP_ROW_OFFSET  = 104;
    /**
     * The GRID_SIDE_ROW_OFFSET class variable is used to define the offset from 0 of the side keys address.
     */
    private static final int GRID_SIDE_ROW_OFFSET = 8;

    /**
     * The CONTROLLER_GRID class variable is used to define the controller to use for accessing the grid.
     */
    private static final int CONTROLLER_GRID = 144;
    /**
     * The CONTROLLER_MASTER class variable is used to define the controller for the menu keys and special functions.
     */
    private static final int CONTROLLER_MASTER = 176;

    /**
     * The KEY_MASTER class variable is used to define the key code for special commands.
     */
    private static final int KEY_MASTER = 0;

    /**
     * The VALUE_RESET class variable is used to define the value sent to the launchpad to clear all keys.
     */
    private static final int VALUE_RESET = 0;
    /**
     * The VALUE_DEFAULT class variable is used to define the value sent to the launchpad to put it in default mode.
     */
    private static final int VALUE_DEFAULT = 32;
    /**
     * The VALUE_FLASHING class variable is used to define the value sent to the launchpad to enable flashing buttons.
     */
    private static final int VALUE_FLASHING = 40;

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
            this.reset();
            this.receiverDevice.close();
            this.transmitterDevice.close();
        }
    }

    public void setFlash(boolean enabled)
    {
        if(this.valid)
        {
            try
            {
                if(enabled)
                {
                    this.sendMessage(CONTROLLER_MASTER, KEY_MASTER, VALUE_FLASHING);
                    this.flashEnabled = true;
                }
                else
                {
                    this.sendMessage(CONTROLLER_MASTER, KEY_MASTER, VALUE_DEFAULT);
                    this.flashEnabled = false;
                }
            }
            catch(InvalidMidiDataException ex)
            {}
        }
    }

    public void reset()
    {
        if(this.valid)
        {
            try
            {
                this.sendMessage(CONTROLLER_MASTER, KEY_MASTER, VALUE_RESET);
            }
            catch(InvalidMidiDataException ex)
            {}
        }
    }

    private void autoEnableFlash(KeyColour colour)
    {
        if(!flashEnabled)
        {
            if(colour.isFlashing())
            {
                this.setFlash(true);
            }
        }
    }

    public void setGridKey(int x, int y, KeyColour colour)
    {
        if(0 <= x && x < GRID_SIZE)
        {
            if(0 <= y && y < GRID_SIZE)
            {
                this.autoEnableFlash(colour);
                try
                {
                    this.sendMessage(CONTROLLER_GRID, x + y * GRID_MULTIPLIER, colour.getColourValue());
                }
                catch(InvalidMidiDataException ex)
                {}
            }
        }
    }

    public void setMenuKey(boolean topRow, int button, KeyColour colour)
    {
        if(0 <= button && button < GRID_SIZE)
        {
            this.autoEnableFlash(colour);
            try
            {
                if(topRow)
                {
                    this.sendMessage(CONTROLLER_MASTER, GRID_TOP_ROW_OFFSET + button, colour.getColourValue());
                }
                else
                {
                    this.sendMessage(CONTROLLER_GRID, GRID_MULTIPLIER * button + GRID_SIDE_ROW_OFFSET, colour.getColourValue());
                }
            }
            catch(InvalidMidiDataException ex)
            {}
        }
    }

    private void sendMessage(int controller, int key, int value) throws InvalidMidiDataException
    {
        if(this.valid)
        {
            this.receiver.send(new ShortMessage(controller, key, value), -1);
        }
    }
}
