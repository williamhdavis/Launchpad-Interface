/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad.Input;

import Launchpad.Constants;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import java.util.ArrayList;
import java.util.List;

public class InputReceiver implements Receiver
{
    /**
     * The VALUE_PRESS class variable is used to define the value sent by the launchpad when a key down event happens.
     */
    private static int VALUE_PRESS = 127;

    /**
     * The listeners instance variable is used to store any listeners that should be sent incoming messages from the launchpad.
     */
    private List<LaunchpadListener> listeners = new ArrayList<>();

    /**
     * The addListener instance variable is used to add a new listener.
     * @param listener - The listener to add.
     */
    public void addListener(LaunchpadListener listener)
    {
        this.listeners.add(listener);
    }

    /**
     * The send instance method is used to format and send launchpad key presses to each set listener.
     * @param message - The midi message from the launchpad.
     * @param timeStamp - The time the message was sent.
     */
    @Override
    public void send(MidiMessage message, long timeStamp)
    {
        byte[] data = message.getMessage();
        if(data.length == 3)
        {
            if(data[0] == Constants.CONTROLLER_MAIN)
            {
                int key = data[1] - Constants.TOP_MENU_OFFSET;
                for(LaunchpadListener listener: this.listeners)
                {
                    if(data[2] == VALUE_PRESS)
                    {
                        listener.menuKeyPressed(Constants.Menu.TOP, key);
                    }
                    else
                    {
                        listener.menuKeyReleased(Constants.Menu.TOP, key);
                    }
                }
            }
            else
            {
                int y = data[1] / Constants.ROW_MULTIPLIER;
                int x = data[1] % Constants.ROW_MULTIPLIER;
                if(x >= 8)
                {
                    for(LaunchpadListener listener: this.listeners)
                    {
                        if(data[2] == VALUE_PRESS)
                        {
                            listener.menuKeyPressed(Constants.Menu.SIDE, y);
                        }
                        else
                        {
                            listener.menuKeyReleased(Constants.Menu.SIDE, y);
                        }
                    }
                }
                else
                {
                    for(LaunchpadListener listener: this.listeners)
                    {
                        if(data[2] == VALUE_PRESS)
                        {
                            listener.gridKeyPressed(x, y);
                        }
                        else
                        {
                            listener.gridKeyReleased(x, y);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void close()
    {

    }
}
