package com.BMS.Command.Common;

import com.BMS.Model.Building;

import java.util.List;

/**
 * The base class for all UndoableCommands.
 */
public class UndoableCommandBase extends CommandBase implements UndoableCommand {

    /**
     * The status of the command.
     */
    protected CmdStatus status;

    public UndoableCommandBase(List<Building> buildings) {
        super(buildings);
        this.status = CmdStatus.PENDING;
    }

    public void execute() {
        status = CmdStatus.EXECUTED;
    }

    public void undo() {
        status = CmdStatus.UNDONE;
    }

    public void redo() {
        status = CmdStatus.REDONE;
    }

    public void printDescription() {
    }


}
