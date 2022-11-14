package com.BMS.Command.Factory;
import com.BMS.Command.*;
import com.BMS.Command.Common.Command;
import com.BMS.Model.*;

import java.util.*;

public abstract class CmdFactoy implements Factory {

    protected List<Building> buildings;

    public static Map<Character, Meta> CommandFactoriesMap = new LinkedHashMap<Character, Meta>() {{
        put('a', 
            new Meta("Add a building", "com.BMS.Command.Factory.AddBuildingCmdFactory"));
        put('d', 
            new Meta("display buildings", "com.BMS.Command.Factory.DisplayBuildingCmdFactory")
        );
        
        put('m', 
            new Meta("modify building", "com.BMS.Command.Factory.ModifyBuildingCmdFactory")
        );
        put('e', 
            new Meta("edit rooms", "com.BMS.Command.Factory.EditBuildingCmdFactory")
        );
    }};

    public abstract Command createCommand();
    public void setArgs(List<Building> buildings) {
        this.buildings = buildings;
    }
}
