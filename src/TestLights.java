/**
 * Created by William Davis on 16/08/2018.
 */
import Launchpad.*;
import Launchpad.Output.CommandMessage;
import Launchpad.Output.CommandMessage.Command;
import Launchpad.Output.GridMessage;
import Launchpad.Output.MenuMessage;

public class TestLights
{
    public static void main(String[] args)
    {
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
                    launchpad.sendMessage(new MenuMessage(Constants.Menu.TOP, x, KeyColour.randomColour(true)));
                    ++x;
                }

                x = 0;
                while(x < 8)
                {
                    launchpad.sendMessage(new MenuMessage(Constants.Menu.SIDE, x, KeyColour.randomColour(true)));
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
