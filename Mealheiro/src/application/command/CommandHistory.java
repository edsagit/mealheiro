/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.command;

import java.util.Stack;

/**
 *
 * @author ed
 */
public class CommandHistory {
    
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    
    public void undo() {
        if (undoStack.size() > 0) {
            Command c = undoStack.remove(undoStack.size()-1);
            c.undo();
        }
        undoStack.removeAllElements();
    }
    
    public void redo() {
        if (redoStack.size() > 0) {
            Command c = redoStack.remove(redoStack.size()-1);
            c.execute();
        }
        redoStack.removeAllElements();
    }
    
    public void addToUndoStack(Command c) {
        undoStack.push(c);
    }
    
    public void addToRedoStack(Command c) {
        redoStack.push(c);
    }
    
  
    public boolean isUndoEmpty() {
        return undoStack.isEmpty();
    }
    
    public boolean isRedoEmpty() {
        return redoStack.isEmpty();
    }
    
}
