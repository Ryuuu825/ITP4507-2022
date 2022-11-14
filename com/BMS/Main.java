package com.BMS;

import java.util.*;

import com.BMS.Utils.*;
import com.BMS.Command.BMSCommandManager;
import com.BMS.Model.*;

public class Main {
    public static String NAME = "Building Management System (BMS)";
    
    public static void main(String[] argv) {
        List<Building> buildings = new ArrayList<Building>();
        BMSCommandManager manager = new BMSCommandManager(buildings);
        
        do {
            System.out.println(NAME);
            manager.printAvailableCommands();

            char cmd = CIN.next().toLowerCase().charAt(0);
            try {
                
                manager.executeCommand(cmd);

            } catch (Exception e) {
                com.BMS.Utils.Logger.debug(e.getLocalizedMessage());
            }

        } while (true);
    }

}
