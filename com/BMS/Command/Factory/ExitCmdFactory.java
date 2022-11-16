package com.BMS.Command.Factory;

import com.BMS.Command.Common.Command;
import com.BMS.Command.ExitCmd;

/**
 * The factory for ExitCmd.
 *
 * @see com.BMS.Command.ExitCmd
 */
public class ExitCmdFactory extends CmdFactoy {

    public Command createCommand() {
        return new ExitCmd();
    }

}
