package com.BMS.Command.Factory;
import com.BMS.Command.*;
import com.BMS.Command.Common.Command;

public class DisplayBuildingCmdFactory extends CmdFactoy {

    public Command createCommand()
    {
        return (Command) new DisplayBuildingCmd(buildings);
    }

}
