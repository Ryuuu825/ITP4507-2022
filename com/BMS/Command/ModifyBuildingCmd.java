package com.BMS.Command;

import com.BMS.Command.Common.CmdStatus;
import com.BMS.Command.Common.UndoableCommandBase;
import com.BMS.Exception.NoSuchBuildingException;
import com.BMS.Model.Building;
import com.BMS.Model.BuildingMemento;
import com.BMS.Model.Memento.BMSCareTaker;
import com.BMS.Utils.CIN;

import java.util.List;

/**
 * The command for modifying the building information.
 */
public class ModifyBuildingCmd extends UndoableCommandBase {

    private BuildingMemento memento;
    private BuildingMemento newMemento;
    private CmdStatus status;
    private int buildingNo;
    


    public ModifyBuildingCmd(List<Building> buildings) {
        super(buildings);
        status = CmdStatus.PENDING;
    }

    public void execute() {
        System.out.print("Building No. : ");
        buildingNo = CIN.nextInt();
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
        status = CmdStatus.EXECUTED;
    }

    public void undo() {
        BMSCareTaker.instance.undo(memento);
        status = CmdStatus.UNDONE;
    }

    public void redo() {
        BMSCareTaker.instance.undo(newMemento);
        status = CmdStatus.REDONE;
    }

    public void printDescription() {
        switch (this.status) {
            case EXECUTED:
            case REDONE :
                System.out.print("Modify Building : ");
                newMemento.printDescription();
                return;
            case UNDONE :
                System.out.print("Modify Building : ");
                memento.printDescription();
                return;
            default : return;
        }
    }
}