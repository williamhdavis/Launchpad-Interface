/**
 * Created by William Davis on 16/08/2018.
 */
import Launchpad.*;
import Launchpad.Messages.CommandMessage;
import Launchpad.Messages.CommandMessage.Command;
import Launchpad.Messages.GridMessage;
import Launchpad.Messages.MenuMessage;
import Launchpad.Messages.MenuMessage.Menu;

public class Main
{
    public static void main(String[] args)
    {
        // DEBUGGING CODE
        try
        {
            Launchpad launchpad = new Launchpad();

            int i = 0;
            while(i < 10)
            {
                launchpad.sendMessage(new CommandMessage(Command.MODE_FLASHING));
                KeyColour[][] data = new KeyColour[8][8];
                int y = 0;
                while(y < 8)
                {
                    int x = 0;
                    while(x < 8)
                    {
                        data[y][x] = KeyColour.randomColour(true);
                        ++x;
                    }
                    ++y;
                }
                launchpad.sendMessage(new GridMessage(data));

                int x = 0;
                while(x < 8)
                {
                    launchpad.sendMessage(new MenuMessage(Menu.TOP, x, KeyColour.randomColour(true)));
                    ++x;
                }

                x = 0;
                while(x < 8)
                {
                    launchpad.sendMessage(new MenuMessage(Menu.SIDE, x, KeyColour.randomColour(true)));
                    ++x;
                }

                Thread.sleep(5000);
                launchpad.sendMessage(new CommandMessage(Command.RESET));
                ++i;
            }

            launchpad.sendMessage(new CommandMessage(Command.MODE_FLASHING));

            launchpad.sendMessage(new GridMessage(new KeyColour[][]{
                {KeyColour.SOLID_GREEN, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.SOLID_AMBER},
                {KeyColour.SOLID_RED, KeyColour.SOLID_GREEN, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.SOLID_AMBER, KeyColour.SOLID_RED},
                {KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_GREEN, KeyColour.FLASHING_RED, KeyColour.FLASHING_RED, KeyColour.SOLID_AMBER, KeyColour.SOLID_RED, KeyColour.SOLID_RED},
                {KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_GREEN, KeyColour.SOLID_AMBER, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED},
                {KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_AMBER, KeyColour.SOLID_GREEN, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED},
                {KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_AMBER, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_GREEN, KeyColour.SOLID_RED, KeyColour.SOLID_RED},
                {KeyColour.SOLID_RED, KeyColour.SOLID_AMBER, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_GREEN, KeyColour.SOLID_RED},
                {KeyColour.SOLID_AMBER, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_RED, KeyColour.SOLID_GREEN},
            }));

            Thread.sleep(5000);
            launchpad.sendMessage(new CommandMessage(Command.RESET));
            launchpad.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
