package com.BMS.Command.Factory;

import com.BMS.Command.Common.Command;
import com.BMS.Command.ModifyBuildingCmd;

/**
 * The factory for ModifyBuildingCmd.
 *
 * @see com.BMS.Command.ModifyBuildingCmd
 */
public class ModifyBuildingCmdFactory extends CmdFactoy {

    public Command createCommand() {
        return new ModifyBuildingCmd(buildings);
    }

}
