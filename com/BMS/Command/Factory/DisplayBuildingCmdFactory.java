package com.BMS.Command.Factory;

import com.BMS.Command.Common.Command;
import com.BMS.Command.DisplayBuildingCmd;

/**
 * The factory for DisplayBuildingCmd.
 *
 * @see com.BMS.Command.DisplayBuildingCmd
 */
public class DisplayBuildingCmdFactory extends CmdFactoy {

    public Command createCommand() {
        return new DisplayBuildingCmd(buildings);
    }

}
