/**
 * Created by William Davis on 17/08/2018.
 */
import javax.sound.midi.*;

public class Launchpad
{
    public static final int NONE = 0;
    public static final int LOW = 1;
    public static final int MID = 2;
    public static final int HIGH = 3;

    public static final boolean SOLID = false;
    public static final boolean FLASHING = true;

    private static final int GRID_WIDTH = 8;
    private static final int GRID_HEIGHT = 8;
    private static final int GRID_MULTIPLIER = 16;

    private static final int BASE = 12;
    private static final int BASE_FLASHING = 8;
    private static final int MULTIPLIER_RED = 1;
    private static final int MULTIPLIER_GREEN = 16;

    private static final int CONTROLLER_GRID = 144;
    private static final int CONTROLLER_MASTER = 176;

    private static final int KEY_MASTER = 0;

    private static final int VALUE_RESET = 0;
    private static final int VALUE_DEFAULT = 32;
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

    public int createColour(int red, int green)
    {
        return this.createColour(red, green, SOLID);
    }

    public int createColour(int red, int green, boolean flashing)
    {
        if(flashing && !this.flashEnabled)
        {
            this.setFlash(true);
        }
        int sum = 0;
        if(flashing)
        {
            sum += BASE_FLASHING;
        }
        else
        {
            sum += BASE;
        }
        sum += MULTIPLIER_RED * red + MULTIPLIER_GREEN * green;
        return sum;
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

    public void setGridKey(int x, int y, int colour)
    {
        if(0 <= x && x < GRID_WIDTH)
        {
            if(0 <= y && y < GRID_HEIGHT)
            {
                try
                {
                    this.sendMessage(CONTROLLER_GRID, x + y * GRID_MULTIPLIER, colour);
                }
                catch(InvalidMidiDataException ex)
                {}
            }
        }
    }

    public void setGridKey(int x, int y, int red, int green)
    {
        this.setGridKey(x, y, this.createColour(red, green, SOLID));
    }

    public void setGridKey(int x, int y, int red, int green, boolean flashing)
    {
        this.setGridKey(x, y, this.createColour(red, green, flashing));
    }

    private void sendMessage(int controller, int key, int value) throws InvalidMidiDataException
    {
        if(this.valid)
        {
            this.receiver.send(new ShortMessage(controller, key, value), -1);
        }
    }
}
