package com.BMS.Command.Factory;

import com.BMS.Command.AddBuildingCmd;
import com.BMS.Command.Common.Command;

/**
 * The factory for AddBuildingCmd.
 *
 * @see com.BMS.Command.AddBuildingCmd
 */
public class AddBuildingCmdFactory extends CmdFactoy {

    public Command createCommand() {
        return new AddBuildingCmd(buildings);
    }

}
