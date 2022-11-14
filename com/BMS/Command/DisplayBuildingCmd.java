package com.BMS.Command;

import java.util.*;

import com.BMS.Command.Common.CommandBase;
import com.BMS.Model.*;
import com.BMS.Utils.*;

public class DisplayBuildingCmd extends CommandBase {

    public final static String DISPLAY_ALL_BUILDNG_STRING_INPUT = "*";

    public DisplayBuildingCmd(List<Building> buildings) {
        super(buildings);
    }

    public void execute()
    {
        System.out.println("Enter Building No.(" + DISPLAY_ALL_BUILDNG_STRING_INPUT + " to display all): ");
        String buildingNo = CIN.next();

        if (buildingNo.equals(DISPLAY_ALL_BUILDNG_STRING_INPUT)) {
            (new DisplayBuildingCmd.DisplayWholeBuildingCmd(this.buildings)).execute();
        } else {
            (new DisplayBuildingCmd.DisplaySpecifyBuildingCmd(this.buildings , buildingNo)).execute();
        }
    }

    
    public class DisplayWholeBuildingCmd extends CommandBase {

        public DisplayWholeBuildingCmd(List<Building> buildings) {
            super(buildings);
        }

        public void execute()
        {
            for (Building b : buildings) {
                b.printBuilding();
            }
        }
       
    }

    public class DisplaySpecifyBuildingCmd extends CommandBase {

        private String buildingNo;

        public DisplaySpecifyBuildingCmd(List<Building> buildings , String buildingNo) {
            super(buildings);
            this.buildingNo = buildingNo;
        }

        public void execute()
        {
            for (Building b : buildings) {
                if (b.getId() == Integer.parseInt(buildingNo)) {
                    System.out.println(b);
                    return;
                }
            }
            System.out.println("Building not found");
        }
    }
}