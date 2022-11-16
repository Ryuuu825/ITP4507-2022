package com.BMS.Command;

import com.BMS.Command.Common.CommandBase;
import com.BMS.Model.Building;
import com.BMS.Utils.CIN;

import java.util.List;

/**
 * The command for displaying the building information.
 */
public class DisplayBuildingCmd extends CommandBase {

    /**
     * The indicator for specify display all buildings.
     * Can be changed in the future.
     */
    public final static String DISPLAY_ALL_BUILDNG_STRING_INPUT = "*";

    public DisplayBuildingCmd(List<Building> buildings) {
        super(buildings);
    }

    public void execute() {
        System.out.println("Enter Building No.(" + DISPLAY_ALL_BUILDNG_STRING_INPUT + " to display all): ");
        String buildingNo = CIN.next();

        if (buildingNo.equals(DISPLAY_ALL_BUILDNG_STRING_INPUT)) {
            (new DisplayWholeBuildingCmd(this.buildings)).execute();
        } else {
            (new DisplaySpecifyBuildingCmd(this.buildings, buildingNo)).execute();
        }
    }


    public static class DisplayWholeBuildingCmd extends CommandBase {

        public DisplayWholeBuildingCmd(List<Building> buildings) {
            super(buildings);
        }

        public void execute() {
            for (Building b : buildings) {
                b.printBuilding();
            }
        }

    }

    public static class DisplaySpecifyBuildingCmd extends CommandBase {

        private final String buildingNo;

        public DisplaySpecifyBuildingCmd(List<Building> buildings, String buildingNo) {
            super(buildings);
            this.buildingNo = buildingNo;
        }

        public void execute() {
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