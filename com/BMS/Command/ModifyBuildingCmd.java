package com.BMS.Command;

import java.util.*;

import com.BMS.Command.Common.UndoableCommandBase;
import com.BMS.Exception.NoSuchBuildingException;
import com.BMS.Model.*;
import com.BMS.Model.Memento.BMSCareTaker;
import com.BMS.Utils.*;

public class ModifyBuildingCmd extends UndoableCommandBase {

    private BuildingMemento memento;
    private BuildingMemento newMemento;
    

    public ModifyBuildingCmd(List<Building> buildings) {
        super(buildings);
    }

    public void execute()
    {
        System.out.print("Building No. : ");
        int buildingNo = CIN.nextInt();
        Building b = null;
        for (Building building : buildings) {
            if (building.getId() == (buildingNo)) {
                b = building;
                break;
            }
        }

        if (b == null) {
            throw new NoSuchBuildingException();
        }
        // backup the building
        memento = (BuildingMemento) BMSCareTaker.instance.save(b);
        b.printBuilding();
        b.modifyBuilding();
        newMemento = (BuildingMemento) BMSCareTaker.instance.save(b);
        super.execute();
    }

    public void undo()
    {
        BMSCareTaker.instance.undo(memento);
        super.undo();
    }

    public void redo() 
    {
        BMSCareTaker.instance.undo(newMemento);
        super.redo();
    }

    public void printDescription()
    {
        switch (this.status) {
            case EXECUTED:
            case REDONE:
                System.out.print("Modify Building : " );
                memento.printDescription();
                return;
            case UNDONE:
                System.out.print("Modify Building : " );
                newMemento.printDescription();
                return;
            default:
                return;
        }
    }
}