package com.BMS.Model;

import com.BMS.Model.Memento.*;

public class RoomMemento implements Memento {

    // double monthlyRental; String supportStaff;
    private double length;
    private double width;
    
    private Room room;

    public RoomMemento(Room room) {
        this.length = room.getLength();
        this.width = room.getWidth();
        this.room = room;
    }

    public void restore() {
        Room room = (Room) this.room;
        room.setLength(length);
        room.setWidth(width);
    }

    public void printDescription() {
        System.out.println("Length: " + length + ", Width: " + width);
    }

    
}
