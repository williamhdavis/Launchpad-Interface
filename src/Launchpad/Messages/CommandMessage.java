/**
 * Created by William Davis on 23/08/2018.
 */
package Launchpad.Messages;

public class CommandMessage extends DefaultMessage
{
    /**
     * The Command enum is used to define the values to send for
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
     * The KEY_MASTER class variable is used to define the key code for special commands.
     */
    private static final byte KEY_MASTER = 0;

    private Command command;

    public CommandMessage(Command command) throws NullPointerException
    {
        if(command == null)
        {
            throw new NullPointerException("Command must not be null.");
        }
        else
        {
            this.command = command;
        }
    }

    @Override
    protected byte[] generateBytes()
    {
        return new byte[]{CONTROLLER_MAIN, KEY_MASTER, (byte)this.command.value};
    }
}
