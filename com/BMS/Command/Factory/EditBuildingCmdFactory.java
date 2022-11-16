package com.BMS.Command.Factory;

import com.BMS.Command.Common.Command;
import com.BMS.Command.EditRoomCmd;
import com.BMS.Model.Building;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The factory for EditRoomCmd.
 * Also, it is holding static class of EditRoom sub commands.
 *
 * @see com.BMS.Command.EditRoomCmd
 */
public class EditBuildingCmdFactory extends CmdFactoy {

    public Command createCommand() {
        return new EditRoomCmd(buildings);
    }

    public abstract static class EditRoomCmdGroupFactory implements Factory {
        public static Map<Character, Meta> CommandFactoriesMap = new LinkedHashMap<>() {{
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

    /**
     * The factory for EditRoomCmd.AddRoomCmd.
     *
     * @see com.BMS.Command.EditRoomCmd.EditRoom_AddRoomCmd
     */
    public static class EditRoom_AddRoomCmdFactory extends EditRoomCmdGroupFactory {
        public Command createCommand() {
            return new EditRoomCmd.EditRoom_AddRoomCmd(building);
        }

    }


    /**
     * The factory for EditRoomCmd.DeleteRoomCmd.
     *
     * @see com.BMS.Command.EditRoomCmd.EditRoom_DeleteRoomCmd
     */
    public static class EditRoom_DeleteRoomCmdFactory extends EditRoomCmdGroupFactory {
        public Command createCommand() {
            return new EditRoomCmd.EditRoom_DeleteRoomCmd(building);
        }

    }


    /**
     * The factory for EditRoomCmd.ModifyRoomCmd.
     *
     * @see com.BMS.Command.EditRoomCmd.EditRoom_ModifyRoomCmd
     */
    public static class EditRoom_ModifyRoomCmdFactory extends EditRoomCmdGroupFactory {
        public Command createCommand() {
            return new EditRoomCmd.EditRoom_ModifyRoomCmd(building);
        }

    }


}
