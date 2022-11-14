package com.BMS.Command.Factory;
import java.util.LinkedHashMap;
import java.util.Map;

import com.BMS.Command.*;
import com.BMS.Command.Common.Command;
import com.BMS.Command.EditRoomCmd.EditRoom_AddRoomCmd;
import com.BMS.Model.Building;

public class EditBuildingCmdFactory extends CmdFactoy {

    public Command createCommand()
    {
        return (Command) new EditRoomCmd(buildings);
    }

    public abstract static class EditRoomCmdGroupFactory implements Factory {
        public static Map<Character, Meta> CommandFactoriesMap = new LinkedHashMap<Character, Meta>() {{
            put('a', 
                new Meta("Add room", "com.BMS.Command.Factory.EditBuildingCmdFactory$EditRoom_AddRoomCmdFactory"));
            put('d',
                new Meta("Delete room", "com.BMS.Command.Factory.EditBuildingCmdFactory$EditRoom_DeleteRoomCmdFactory")
            );
            put('m',
                new Meta("Modify room", "com.BMS.Command.Factory.EditBuildingCmdFactory$EditRoom_ModifyRoomCmdFactory")
            );
            
        }};
    
        protected Building building;
    
        public abstract Command createCommand();
        public void setArgs(Building building) {
            this.building = building;
        }
    }

    public static class EditRoom_AddRoomCmdFactory extends EditRoomCmdGroupFactory {
        public Command createCommand()
        {
            return new EditRoomCmd.EditRoom_AddRoomCmd(building);
        }
        
    }

    public static class EditRoom_DeleteRoomCmdFactory extends EditRoomCmdGroupFactory {
        public Command createCommand()
        {
            return new EditRoomCmd.EditRoom_DeleteRoomCmd(building);
        }
        
    }

    public static class EditRoom_ModifyRoomCmdFactory extends EditRoomCmdGroupFactory {
        public Command createCommand()
        {
            return new EditRoomCmd.EditRoom_ModifyRoomCmd(building);
        }
        
    }
    

}
