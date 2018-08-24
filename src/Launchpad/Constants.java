/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad;

public class Constants
{
    /**
     * The Menu enum is used to set which of the launchpads menu buttons is being accessed.
     */
    public enum Menu {TOP, SIDE};

    /**
     * The CONTROLLER_MAIN class variable is used to define the controller for the menu keys and special functions.
     */
    public static final byte CONTROLLER_MAIN = (byte)176;
    /**
     * The CONTROLLER_GRID class variable is used to define the controller to use for accessing the grid.
     */
    public static final byte CONTROLLER_GRID = (byte)144;
    /**
     * The CONTROLLER_FULL class variable is used to define the controller for mass setting all key colours.
     */
    public static final byte CONTROLLER_FULL = (byte)146;

    /**
     * The ROW_MULTIPLIER class variable is used to define the change in key number on the y-axis of the launchpad.
     */
    public static final int ROW_MULTIPLIER = 16;

    /**
     * The TOP_MENU_OFFSET class variable is used to define the offset from 0 of the top keys address.
     */
    public static final int TOP_MENU_OFFSET  = 104;
}
