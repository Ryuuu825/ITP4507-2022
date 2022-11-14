package com.BMS.Command.Factory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.BMS.Command.Common.Command;
import com.BMS.Model.Building;

public abstract class EditRoomCmdGroupFactory implements Factory {
    public static Map<Character, Meta> CommandFactoriesMap = new LinkedHashMap<Character, Meta>() {{
        put('a', 
            new Meta("Add room", "com.BMS.Command.Factory.EditRoom_AddRoomCmdFactory"));
        
    }};

    protected Building building;

    public abstract Command createCommand();
    public void setArgs(Building building) {
        this.building = building;
    }
}

