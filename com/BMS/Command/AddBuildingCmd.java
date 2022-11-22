package com.BMS.Command;

import com.BMS.Command.Common.UndoableCommandBase;
import com.BMS.Exception.CouldNotCreateInstanceException;
import com.BMS.Model.Building;
import com.BMS.Model.Factory.BuildingFactory;
import com.BMS.Utils.CIN;

import java.util.List;

/**
 * The command to add a building.
 */
public class AddBuildingCmd extends UndoableCommandBase {

    private Building building;
    private String undoString ;

    public AddBuildingCmd(List<Building> buildings) {
        super(buildings);
    }

    public void execute() {
        // print all available building types
        System.out.print("Enter Building Type: (");

        for (int i = 0; i < Building.buildingType.keySet().size(); i++) {
            Character key = (Character) Building.buildingType.keySet().toArray()[i];
            String value = Building.buildingType.get(key);

            if (i < Building.buildingType.keySet().size() - 1)
                System.out.printf("%c=%s / ", key, value);
            else
                System.out.printf("%c=%s):\n", key, value);
        }


        String type = CIN.next();
        BuildingFactory bf;
        try {

            // get the factory from the name of runtime building type
            bf = (BuildingFactory) Class.forName("com.BMS.Model.Factory." + Building.buildingType.get(type.charAt(0)) + "Factory")
                .getDeclaredConstructor().newInstance();
            building = bf.createBuilding();

            undoString = building.toString();
            

        } catch (NullPointerException e) {
            throw new CouldNotCreateInstanceException("Building");
        } catch (Exception e) {
            throw new CouldNotCreateInstanceException("com.BMS.Model.Factory." + Building.buildingType.get(type.charAt(0)) + "Factory");
        }


        buildings.add(building);

        // print info about the building
        System.out.println("New Building Added:\n" );
        building.printBuilding();



    }


    public void undo() {
        buildings.remove(building);
    }

    public void redo() {
        buildings.add(building);
    }


    public void printDescription() {
        System.out.print("Add Building : ");
        System.out.println(undoString);
       
    }


}