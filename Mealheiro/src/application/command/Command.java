package application.command;

/**
 *
 * @author ed
 */
public interface Command {
    
    /**
     * undo function
     */
    public void undo();

    /**
     * execute function
     */
    public void execute();
}
