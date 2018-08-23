/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad.Messages;

import Launchpad.KeyColour;

public class MenuMessage extends DefaultMessage
{
    public enum Menu {TOP, SIDE};

    /**
     * The TOP_ROW_OFFSET class variable is used to define the offset from 0 of the top keys address.
     */
    private static final int TOP_ROW_OFFSET  = 104;

    private boolean topMenu;
    private int key;
    private KeyColour colour;

    public MenuMessage(Menu menu, int button, KeyColour colour) throws NullPointerException, IndexOutOfBoundsException
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
        if(menu == Menu.TOP)
        {
            this.topMenu = true;
        }
        else
        {
            this.topMenu = false;
        }
        this.key = button;
        this.colour = colour;
    }

    @Override
    protected byte[] generateBytes()
    {
        byte[] data = new byte[3];
        if(this.topMenu)
        {
            data[0] = CONTROLLER_MAIN;
            data[1] = (byte)(TOP_ROW_OFFSET + this.key);
        }
        else
        {
            data[0] = CONTROLLER_GRID;
            data[1] = (byte)(ROW_MULTIPLIER * this.key + GRID_SIZE);
        }
        data[2] = (byte)this.colour.getColourValue();
        return data;
    }
}
