package application.command;

import java.util.Stack;

/**
 *
 * @author ed
 */
public class CommandHistory {

    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    
    /**
     * undo function - iterates undo stack and executes commands in them
     */
    public void undo() {
        if (undoStack.size() > 0) {
            Command c = undoStack.remove(undoStack.size() - 1);
            c.undo();
        }
        undoStack.removeAllElements();
    }
    
    /**
     * redo function - iterates redo stack and executes commands in them
     */
    public void redo() {
        if (redoStack.size() > 0) {
            Command c = redoStack.remove(redoStack.size() - 1);
            c.execute();
        }
        redoStack.removeAllElements();
    }
    
    /**
     * 
     * @param c Command - command to push to undo stack
     */
    public void addToUndoStack(Command c) {
        undoStack.push(c);
    }
    
    /**
     * 
     * @param c Command - command to push to redo stack
     */
    public void addToRedoStack(Command c) {
        redoStack.push(c);
    }
    
    /**
     * 
     * @return undo stack is empty, returns true if its empty, false if contains items
     */
    public boolean isUndoEmpty() {
        return undoStack.isEmpty();
    }
    
    /**
     * 
     * @return redo stack is empty, returns true if its empty, false if contains items
     */
    public boolean isRedoEmpty() {
        return redoStack.isEmpty();
    }

}
