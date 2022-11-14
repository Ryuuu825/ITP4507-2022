package com.BMS.Command;

import com.BMS.Command.Common.Command;

public class ExitCmd implements Command {


    public ExitCmd() {
    }

    public void execute()
    {
        System.out.println("--Exit--");
        System.exit(0);
    }
}