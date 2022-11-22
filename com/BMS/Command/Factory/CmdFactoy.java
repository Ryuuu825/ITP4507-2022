package com.BMS.Command.Factory;

import com.BMS.Command.Common.Command;
import com.BMS.Model.Building;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The factory for all commands.
 */
public abstract class CmdFactoy implements Factory {

    protected List<Building> buildings;

    public final static Map<Character, Meta> CommandFactoriesMap = new LinkedHashMap<>() {{
        put('a',
                new Meta("Add a building", "com.BMS.Command.Factory.AddBuildingCmdFactory"));
        put('d',
                new Meta("display buildings", "com.BMS.Command.Factory.DisplayBuildingCmdFactory")
        );

        put('m',
                new Meta("modify building", "com.BMS.Command.Factory.ModifyBuildingCmdFactory")
        );
        put('e',
                new Meta("edit rooms", "com.BMS.Command.Factory.EditRoomCmdFactory")
        );

    }};

    public abstract Command createCommand();

    public void setArgs(List<Building> buildings) {
        this.buildings = buildings;
    }
}
