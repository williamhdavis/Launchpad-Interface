/**
 * Created by William Davis on 22/08/2018.
 */
package Launchpad.Output;

import Launchpad.Constants;
import Launchpad.KeyColour;

public class KeyMessage extends DefaultMessage
{
    private int x;
    private int y;
    private KeyColour colour;

    public KeyMessage(int x, int y, KeyColour colour) throws IndexOutOfBoundsException, NullPointerException
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
            this.x = x;
            this.y = y;
            this.colour = colour;
        }
    }

    @Override
    protected byte[] generateBytes()
    {
        byte key = (byte)(x + y * Constants.ROW_MULTIPLIER);
        return new byte[]{Constants.CONTROLLER_GRID, key, (byte)colour.getColourValue()};
    }
}
