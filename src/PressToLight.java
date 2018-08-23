/**
 * Created by William Davis on 23/08/2018.
 */
import Launchpad.Constants;
import Launchpad.Input.LaunchpadListener;
import Launchpad.*;
import Launchpad.Output.KeyMessage;
import Launchpad.Output.MenuMessage;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class PressToLight implements LaunchpadListener
{
    private Launchpad launchpad;

    public PressToLight() throws MidiUnavailableException
    {
        this.launchpad = new Launchpad();
        this.launchpad.getTransmitter().addListener(this);
    }

    @Override
    public void menuKeyPressed(Constants.Menu menu, int key)
    {
        try
        {
            this.launchpad.sendMessage(new MenuMessage(menu, key, KeyColour.SOLID_RED));
        }
        catch(InvalidMidiDataException ex)
        {}
    }

    @Override
    public void menuKeyReleased(Constants.Menu menu, int key)
    {
        try
        {
            this.launchpad.sendMessage(new MenuMessage(menu, key, KeyColour.NONE));
        }
        catch(InvalidMidiDataException ex)
        {}
    }

    @Override
    public void gridKeyPressed(int x, int y)
    {
        try
        {
            this.launchpad.sendMessage(new KeyMessage(x, y, KeyColour.SOLID_GREEN));
        }
        catch(InvalidMidiDataException ex)
        {}
    }

    @Override
    public void gridKeyReleased(int x, int y)
    {
        try
        {
            this.launchpad.sendMessage(new KeyMessage(x, y, KeyColour.NONE));
        }
        catch(InvalidMidiDataException ex)
        {}
    }

    public static void main(String[] args)
    {
        try
        {
            new PressToLight();
        }
        catch(MidiUnavailableException ex)
        {
            ex.printStackTrace();
        }
    }
}
