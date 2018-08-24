/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad.Output;

import Launchpad.Constants;
import Launchpad.KeyColour;

public class Message
{
    /**
     * The Command enum is used to define the values to send for given launchpad built in commands.
     */
    public enum Command
    {
        RESET(0),
        MODE_NORMAL (32),
        MODE_FLASHING (40);

        private int value;
        Command(int value)
        {
            this.value = value;
        }
    };

    /**
     * The GRID_SIZE class variable is used to define the width and height of the square button grid on the launchpad.
     */
    private static final int GRID_SIZE = 8;
    /**
     * The KEY_MASTER class variable is used to define the key code for special commands.
     */
    private static final byte KEY_MASTER = 0;

    /**
     * The data instance variable is used to store the data values to be sent to the launchpad.
     */
    private byte[] data;

    /**
     * This Message constructor is used to create a new menu formatted message.
     * @param menu - The menu bar on the launchpad to set.
     * @param button - The button on the menu bar to set.
     * @param colour - The colour to set the button to.
     * @throws NullPointerException - Thrown if menu or colour are null.
     * @throws IndexOutOfBoundsException - Thrown if button is outside the configured range (0 to 7).
     */
    public Message(Constants.Menu menu, int button, KeyColour colour) throws NullPointerException, IndexOutOfBoundsException
    {
        if(menu == null)
        {
            throw new NullPointerException("Menu must not be null.");
        }
        else if(colour == null)
        {
            throw new NullPointerException("Colour must not be null.");
        }
        else if(button < 0 || GRID_SIZE < button)
        {
            throw new IndexOutOfBoundsException("Button must be between 0 and " + GRID_SIZE + ".");
        }

        this.data = new byte[3];
        if(menu == Constants.Menu.TOP)
        {
            this.data[0] = Constants.CONTROLLER_MAIN;
            this.data[1] = (byte)(Constants.TOP_MENU_OFFSET + button);
        }
        else
        {
            this.data[0] = Constants.CONTROLLER_GRID;
            this.data[1] = (byte)(Constants.ROW_MULTIPLIER * button + GRID_SIZE);
        }
        this.data[2] = (byte)colour.getColourValue();
    }

    /**
     * This Message constructor is used to create a new full grid formatted message.
     * @param grid - A grid of colours the same size as the square button area on the launchpad.
     * @throws NullPointerException - Thrown if grid is null.
     * @throws IndexOutOfBoundsException - Thrown if the dimensions of grid are not the same as the launchpad. (8 by 8)
     */
    public Message(KeyColour[][] grid) throws NullPointerException, IndexOutOfBoundsException
    {
        if(grid == null)
        {
            throw new NullPointerException("Grid must not be null.");
        }
        if(grid.length != GRID_SIZE)
        {
            throw new IndexOutOfBoundsException("Grid does not contain " + GRID_SIZE + " rows.");
        }
        else
        {
            int y = 0;
            while(y < grid.length)
            {
                if(grid[y] == null)
                {
                    throw new NullPointerException("Grid row " + (y + 1) + " must not be null.");
                }
                if(grid[y].length != GRID_SIZE)
                {
                    throw new IndexOutOfBoundsException("Row " + (y + 1) + " length is not " + GRID_SIZE + ".");
                }
                ++y;
            }

            this.data = new byte[GRID_SIZE * GRID_SIZE + 1];
            this.data[0] = Constants.CONTROLLER_FULL;
            int v = 1;
            y = 0;
            while(y < grid.length)
            {
                int x = 0;
                while(x < grid[y].length)
                {
                    this.data[v] = (byte)grid[y][x].getColourValue();
                    ++x;
                    ++v;
                }
                ++y;
            }
        }
    }

    /**
     * This Message constructor is used to create a new single grid key formatted message.
     * @param x - The x co-ordinate of the key.
     * @param y - The y co-ordinate of the key.
     * @param colour - The colour to set the key.
     * @throws NullPointerException - Thrown if colour is null.
     * @throws IndexOutOfBoundsException - Thrown if x or y are outside the configured range (0 to 7).
     */
    public Message(int x, int y, KeyColour colour) throws NullPointerException, IndexOutOfBoundsException
    {
        if(colour == null)
        {
            throw new NullPointerException("The colour must not be null.");
        }
        else if(x < 0 && GRID_SIZE < x)
        {
            throw new IndexOutOfBoundsException("X value must be between 0 and " + GRID_SIZE + ".");
        }
        else if(y < 0 && GRID_SIZE < y)
        {
            throw new IndexOutOfBoundsException("Y value must be between 0 and " + GRID_SIZE + ".");
        }
        else
        {
            byte key = (byte)(x + y * Constants.ROW_MULTIPLIER);
            this.data = new byte[]{Constants.CONTROLLER_GRID, key, (byte)colour.getColourValue()};
        }
    }

    /**
     * This Message constructor is used to create a new command formatted message.
     * @param command - The command to send to the launchpad.
     * @throws NullPointerException - Thrown if command is null.
     */
    public Message(Command command) throws NullPointerException
    {
        if(command == null)
        {
            throw new NullPointerException("Command must not be null.");
        }
        else
        {
            this.data = new byte[]{Constants.CONTROLLER_MAIN, KEY_MASTER, (byte)command.value};
        }
    }

    /**
     * The getData instance method is used to get the messages formatted data ready for sending.
     * @return - The formatted data.
     */
    public byte[] getData()
    {
        return this.data;
    }
}
