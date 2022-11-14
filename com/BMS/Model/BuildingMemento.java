package com.BMS.Model;

import java.util.ArrayList;

import com.BMS.Model.Memento.*;

public class BuildingMemento implements Memento {
    public Building building;

    // state
    // protected int id; // setId() is not aviailable and seem not necessary to save the state
    protected java.util.ArrayList<Room> rooms;

    public BuildingMemento(Building building) {
        this.building = building;
        this.rooms = new ArrayList<>(building.getRoomQty());

        for (Room room : building.getRooms()) {
            rooms.add((Room) room) ;
        }
    }

    public void restore()
    {
        building.getRooms().clear();
		for(int i = 0; i < rooms.size(); i++) {
			building.getRooms().add(rooms.get(i));
		}
    }

    public void printDescription() {
        building.printBuilding();
    }
}
