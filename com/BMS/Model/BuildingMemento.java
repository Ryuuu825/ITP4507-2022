package com.BMS.Model;

import com.BMS.Model.Memento.Memento;

import java.util.ArrayList;

/**
 * The Memento class for Building.
 */
public class BuildingMemento implements Memento {
    public Building building;

    // state
    // protected int id; // setId() is not aviailable and seem not necessary to save the state
    protected java.util.ArrayList<Room> rooms;

    public BuildingMemento(Building building) {
        this.building = building;
        this.rooms = new ArrayList<>(building.getRoomQty());

        rooms.addAll(building.getRooms());
    }

    public void restore() {
        building.getRooms().clear();
        for (Room room : rooms) {
            building.getRooms().add(room);
        }
    }

    public void printDescription() {
        building.printBuilding();
    }
}
