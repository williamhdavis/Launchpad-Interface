/**
 * Created by William Davis on 16/08/2018.
 */
import Launchpad.*;

public class Main
{
    public static void main(String[] args)
    {
        // DEBUGGING CODE
        try
        {
            Launchpad launchpad = new Launchpad();

            int x = 0;
            while(x < 8)
            {
                int y = 0;
                while(y < 8)
                {
                    launchpad.setGridKey(x, y, KeyColour.randomColour(true));
                    ++y;
                }
                ++x;
            }

            x = 0;
            while(x < 8)
            {
                launchpad.setMenuKey(true, x, KeyColour.randomColour(true));
                ++x;
            }

            x = 0;
            while(x < 8)
            {
                launchpad.setMenuKey(false, x, KeyColour.randomColour(true));
                ++x;
            }

            Thread.sleep(5000);
            launchpad.reset();
            launchpad.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
