/**
 * Created by William Davis on 16/08/2018.
 */
public class Main
{
    public static void main(String[] args)
    {
        // DEBUGGING CODE
        try
        {
            Launchpad launchpad = new Launchpad();

            launchpad.setGridKey(0, 0, launchpad.createColour(Launchpad.NONE, Launchpad.NONE));
            launchpad.setGridKey(1, 0, launchpad.createColour(Launchpad.LOW, Launchpad.NONE));
            launchpad.setGridKey(2, 0, launchpad.createColour(Launchpad.MID, Launchpad.NONE));
            launchpad.setGridKey(3, 0, launchpad.createColour(Launchpad.HIGH, Launchpad.NONE, true));

            launchpad.setGridKey(0, 1, launchpad.createColour(Launchpad.NONE, Launchpad.LOW));
            launchpad.setGridKey(1, 1, launchpad.createColour(Launchpad.LOW, Launchpad.LOW));
            launchpad.setGridKey(2, 1, launchpad.createColour(Launchpad.MID, Launchpad.LOW));
            launchpad.setGridKey(3, 1, launchpad.createColour(Launchpad.HIGH, Launchpad.LOW));

            launchpad.setGridKey(0, 2, launchpad.createColour(Launchpad.NONE, Launchpad.MID));
            launchpad.setGridKey(1, 2, launchpad.createColour(Launchpad.LOW, Launchpad.MID));
            launchpad.setGridKey(2, 2, launchpad.createColour(Launchpad.MID, Launchpad.MID));
            launchpad.setGridKey(3, 2, launchpad.createColour(Launchpad.HIGH, Launchpad.MID));

            launchpad.setGridKey(0, 3, launchpad.createColour(Launchpad.NONE, Launchpad.HIGH, true));
            launchpad.setGridKey(1, 3, launchpad.createColour(Launchpad.LOW, Launchpad.HIGH));
            launchpad.setGridKey(2, 3, launchpad.createColour(Launchpad.MID, Launchpad.HIGH));
            launchpad.setGridKey(3, 3, launchpad.createColour(Launchpad.HIGH, Launchpad.HIGH, true));

            launchpad.setMenuKey(true, 0, 60);
            launchpad.setMenuKey(true, 1, 60);
            launchpad.setMenuKey(true, 2, 60);
            launchpad.setMenuKey(true, 3, 60);
            launchpad.setMenuKey(true, 4, 60);
            launchpad.setMenuKey(true, 5, 60);
            launchpad.setMenuKey(true, 6, 60);
            launchpad.setMenuKey(true, 7, 60);

            launchpad.setMenuKey(false, 0, 60);
            launchpad.setMenuKey(false, 1, 60);
            launchpad.setMenuKey(false, 2, 60);
            launchpad.setMenuKey(false, 3, 60);
            launchpad.setMenuKey(false, 4, 60);
            launchpad.setMenuKey(false, 5, 60);
            launchpad.setMenuKey(false, 6, 60);
            launchpad.setMenuKey(false, 7, 60);

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
