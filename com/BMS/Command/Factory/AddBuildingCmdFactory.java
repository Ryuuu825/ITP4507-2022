package com.BMS.Command.Factory;
import com.BMS.Command.*;
import com.BMS.Command.Common.Command;

public class AddBuildingCmdFactory extends CmdFactoy {


    public Command createCommand()
    {
        return (Command) new AddBuildingCmd(buildings);
    }

}
