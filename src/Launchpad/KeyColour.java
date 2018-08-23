/**
 * Created by William Davis on 21/08/2018.
 */
package Launchpad;

import java.util.Random;

public class KeyColour
{
    /**
     * The LEVEL enum is used to define the accepted LED brightnesses.
     */
    public enum Level
    {
        NONE (0),
        LOW (1),
        MID (2),
        HIGH (3);

        private int value;
        Level(int value)
        {
            this.value = value;
        }
    };

    /**
     * The SOLID_GREEN class variable is used to provide a preset for a solid green key.
     */
    public static final KeyColour SOLID_GREEN = new KeyColour(Level.NONE, Level.HIGH);
    /**
     * The FLASHING_GREEN class variable is used to provide a preset for a flashing green key.
     */
    public static final KeyColour FLASHING_GREEN = new KeyColour(Level.NONE, Level.HIGH, true);
    /**
     * The SOLID_RED class variable is used to provide a preset for a solid red key.
     */
    public static final KeyColour SOLID_RED = new KeyColour(Level.HIGH, Level.NONE);
    /**
     * The FLASHING_RED class variable is used to provide a preset for a flashing red key.
     */
    public static final KeyColour FLASHING_RED = new KeyColour(Level.HIGH, Level.NONE, true);
    /**
     * The SOLID_AMBER class variable is used to provide a preset for a solid amber key.
     */
    public static final KeyColour SOLID_AMBER = new KeyColour(Level.HIGH, Level.HIGH);
    /**
     * The FLASHING_AMBER class variable is used to provide a preset for a flashing amber key.
     */
    public static final KeyColour FLASHING_AMBER = new KeyColour(Level.HIGH, Level.HIGH, true);

    /**
     * The BASE class variable defines the base value for any solid colour.
     */
    private static final int BASE = 12;
    /**
     * The BASE_FLASHING class variable defines the base value for any flashing colour.
     */
    private static final int BASE_FLASHING = 8;

    /**
     * The MULTIPLIER_GREEN class variable is used to define the multiplier required for green colour values.
     */
    private static final int MULTIPLIER_GREEN = 16;

    /**
     * The flashing instance variable is used to track if the colour should flash on the button.
     */
    private boolean flashing;
    /**
     * The colourValue instance variable is used to store the integer value that is the colour desired.
     */
    private int colourValue;

    /**
     * The KeyColour constructor is used to create a new colour.
     * @param red - The intensity of the red in the colour.
     * @param green - The intensity of the green in the colour.
     */
    public KeyColour(Level red, Level green)
    {
        this(red, green, false);
    }

    /**
     * The KeyColour constructor is used to create a new colour.
     * @param red - The intensity of the red in the colour.
     * @param green - The intensity of the green in the colour.
     * @param flashing - A flag to set if the colour should flash.
     */
    public KeyColour(Level red, Level green, boolean flashing) throws NullPointerException
    {
        if(red == null)
        {
            throw new NullPointerException("Red must not be null.");
        }
        else if(green == null)
        {
            throw new NullPointerException("Green must not be null.");
        }
        this.flashing = flashing;
        this.colourValue = 0;
        if(flashing)
        {
            this.colourValue += BASE_FLASHING;
        }
        else
        {
            this.colourValue += BASE;
        }
        int r = red.value;
        if(r < Level.NONE.value)
        {
            r = Level.NONE.value;
        }
        else if(r > Level.HIGH.value)
        {
            r = Level.HIGH.value;
        }
        int g = green.value;
        if(g < Level.NONE.value)
        {
            g = Level.NONE.value;
        }
        else if(g > Level.HIGH.value)
        {
            g = Level.HIGH.value;
        }
        this.colourValue += r + MULTIPLIER_GREEN * g;
    }

    /**
     * The isFlashing instance method is used to check if the colour is set to flash.
     * @return - True if the colour should flash. False otherwise.
     */
    public boolean isFlashing()
    {
        return this.flashing;
    }

    /**
     * The getColourValue instance method is used to get the integer value of the colour.
     * @return - The value of the colour.
     */
    public int getColourValue()
    {
        return this.colourValue;
    }

    /**
     * The randomColour class method is used to generate a random launchpad colour.
     * @return - The generated colour.
     */
    public static KeyColour randomColour()
    {
        return randomColour(false);
    }

    /**
     * The randomColour class method is used to generate a random launchpad colour.
     * @param flashing - Should the randomly generated colour have the option of flashing.
     * @return - The generated colour.
     */
    public static KeyColour randomColour(boolean flashing)
    {
        Random r = new Random();
        boolean red = r.nextBoolean();
        Level re = Level.NONE;
        if(red)
        {
            re = Level.HIGH;
        }
        boolean green  = r.nextBoolean();
        Level gr = Level.NONE;
        if(green)
        {
            gr = Level.HIGH;
        }
        boolean flash = false;
        if(flashing)
        {
            flash = r.nextBoolean();
        }
        return new KeyColour(re, gr, flash);
    }
}
