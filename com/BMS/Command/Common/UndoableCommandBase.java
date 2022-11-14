package com.BMS.Command.Common;

import java.util.*;
import com.BMS.Model.*;

public class UndoableCommandBase extends CommandBase implements UndoableCommand {

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
        return;
    }

    
}
