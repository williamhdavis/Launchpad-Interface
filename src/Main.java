/**
 * Created by William Davis on 16/08/2018.
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Launchpad launchpad = new Launchpad();

            launchpad.setGridKey(0, 0, launchpad.createColour(Launchpad.NONE, Launchpad.NONE));
            launchpad.setGridKey(1, 0, launchpad.createColour(Launchpad.LOW, Launchpad.NONE));
            launchpad.setGridKey(2, 0, launchpad.createColour(Launchpad.MID, Launchpad.NONE));
            launchpad.setGridKey(3, 0, launchpad.createColour(Launchpad.HIGH, Launchpad.NONE, Launchpad.FLASHING));

            launchpad.setGridKey(0, 1, launchpad.createColour(Launchpad.NONE, Launchpad.LOW, Launchpad.SOLID));
            launchpad.setGridKey(1, 1, launchpad.createColour(Launchpad.LOW, Launchpad.LOW, Launchpad.SOLID));
            launchpad.setGridKey(2, 1, launchpad.createColour(Launchpad.MID, Launchpad.LOW, Launchpad.SOLID));
            launchpad.setGridKey(3, 1, launchpad.createColour(Launchpad.HIGH, Launchpad.LOW, Launchpad.SOLID));

            launchpad.setGridKey(0, 2, launchpad.createColour(Launchpad.NONE, Launchpad.MID, Launchpad.SOLID));
            launchpad.setGridKey(1, 2, launchpad.createColour(Launchpad.LOW, Launchpad.MID, Launchpad.SOLID));
            launchpad.setGridKey(2, 2, launchpad.createColour(Launchpad.MID, Launchpad.MID, Launchpad.SOLID));
            launchpad.setGridKey(3, 2, launchpad.createColour(Launchpad.HIGH, Launchpad.MID, Launchpad.SOLID));

            launchpad.setGridKey(0, 3, launchpad.createColour(Launchpad.NONE, Launchpad.HIGH, Launchpad.FLASHING));
            launchpad.setGridKey(1, 3, launchpad.createColour(Launchpad.LOW, Launchpad.HIGH, Launchpad.SOLID));
            launchpad.setGridKey(2, 3, launchpad.createColour(Launchpad.MID, Launchpad.HIGH, Launchpad.SOLID));
            launchpad.setGridKey(3, 3, launchpad.createColour(Launchpad.HIGH, Launchpad.HIGH, Launchpad.FLASHING));

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
