/**
 * Created by William Davis on 22/08/2018.
 */
package Launchpad.Messages;

import Launchpad.KeyColour;

public class GridMessage extends DefaultMessage
{
    private KeyColour[][] data;

    public GridMessage(KeyColour[][] grid) throws IndexOutOfBoundsException, NullPointerException
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
            this.data = grid;
        }
    }

    @Override
    protected byte[] generateBytes()
    {
        byte[] values = new byte[GRID_SIZE * GRID_SIZE + 1];
        values[0] = CONTROLLER_FULL;
        int v = 1;
        int y = 0;
        while(y < this.data.length)
        {
            int x = 0;
            while(x < this.data[y].length)
            {
                values[v] = (byte)this.data[y][x].getColourValue();
                ++x;
                ++v;
            }
            ++y;
        }
        return values;
    }
}
