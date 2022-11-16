package com.BMS.Command.Common;

import com.BMS.Model.Building;

import java.util.List;


/**
 * The base class for all commands.
 */
public abstract class CommandBase implements Command {

    protected List<Building> buildings;

    public CommandBase(List<Building> buildings) {
        this.buildings = buildings;
    }

}
