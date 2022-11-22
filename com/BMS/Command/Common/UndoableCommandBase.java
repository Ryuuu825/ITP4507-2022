package com.BMS.Command.Common;

import com.BMS.Model.Building;

import java.util.List;

/**
 * The base class for all UndoableCommands.
 */
public abstract class UndoableCommandBase extends CommandBase implements UndoableCommand {

    /**
     * The status of the command.
     */

    public UndoableCommandBase(List<Building> buildings) {
        super(buildings);
    }

    public void printDescription() {
    }


}
