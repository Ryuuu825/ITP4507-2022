package com.BMS.Command.Common;

/**
 * The interface of command that can be undone.
 */
public interface UndoableCommand extends Command {

    void execute();

    /**
     * Undo the command.
     */
    void undo();

    /**
     * Redo the command.
     */
    void redo();

    /**
     * return the description of this command based on the current state
     */
    void printDescription();
}
