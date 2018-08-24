/**
 * Created by William Davis on 16/08/2018.
 */
import Launchpad.*;
import Launchpad.Output.Message.Command;
import Launchpad.Output.Message;

public class TestLights
{
    public static void main(String[] args)
    {
        try
        {
            Device launchpad = new Device();

            int i = 0;
            while(i < 10)
            {
                launchpad.sendMessage(new Message(Command.MODE_FLASHING));
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
                launchpad.sendMessage(new Message(data));

                int x = 0;
                while(x < 8)
                {
                    launchpad.sendMessage(new Message(Constants.Menu.TOP, x, KeyColour.randomColour(true)));
                    ++x;
                }

                x = 0;
                while(x < 8)
                {
                    launchpad.sendMessage(new Message(Constants.Menu.SIDE, x, KeyColour.randomColour(true)));
                    ++x;
                }

                Thread.sleep(5000);
                launchpad.sendMessage(new Message(Command.RESET));
                ++i;
            }

            launchpad.sendMessage(new Message(Command.MODE_FLASHING));

            launchpad.sendMessage(new Message(new KeyColour[][]{
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
            launchpad.sendMessage(new Message(Command.RESET));
            launchpad.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
