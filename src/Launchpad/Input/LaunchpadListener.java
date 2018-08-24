/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad.Input;

import Launchpad.Constants;

public interface LaunchpadListener
{
    /**
     * The menuKeyPressed instance method is used to respond to menu key down events.
     * @param menu - The set of menu keys on the launchpad pressed.
     * @param key - The key pressed.
     */
    void menuKeyPressed(Constants.Menu menu, int key);

    /**
     * The menuKeyReleased instance method is used to respond to menu key up events.
     * @param menu - The set of menu keys on the launchpad released.
     * @param key - The key released.
     */
    void menuKeyReleased(Constants.Menu menu, int key);

    /**
     * The gridKeyPressed instance method is used to respond to grid key down events.
     * @param x - The x co-ordinate of the key pressed.
     * @param y - The y co-ordinate of the key pressed.
     */
    void gridKeyPressed(int x, int y);

    /**
     * The gridKeyReleased instance method is used to respond to grid key up events.
     * @param x - The x co-ordinate of the key released.
     * @param y - The y co-ordinate of the key released.
     */
    void gridKeyReleased(int x, int y);
}
