package com.BMS.Command.Common;

import java.util.*;
import com.BMS.Model.*;

public class CommandBase implements Command {

    protected List<Building>  buildings;

    public CommandBase(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void execute() {
    }
}
