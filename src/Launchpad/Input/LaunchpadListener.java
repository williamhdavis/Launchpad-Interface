/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad.Input;

import Launchpad.Constants;

public interface LaunchpadListener
{
    void menuKeyPressed(Constants.Menu menu, int key);
    void menuKeyReleased(Constants.Menu menu, int key);
    void gridKeyPressed(int x, int y);
    void gridKeyReleased(int x, int y);
}
