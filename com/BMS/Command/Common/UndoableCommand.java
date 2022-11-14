package com.BMS.Command.Common;

public interface UndoableCommand extends Command {
    public void execute();
    public void undo();
    public void redo();

    // return the command description based on the current state
    public void printDescription();  
}
