package com.BMS.Model;

import com.BMS.Model.Memento.Memento;

/**
 * The Memento class for room.
 *
 * @see com.BMS.Model.Room
 */
public class RoomMemento implements Memento {

    // double monthlyRental; String supportStaff;
    private final double length;
    private final double width;

    private final Room room;

    public RoomMemento(Room room) {
        this.length = room.getLength();
        this.width = room.getWidth();
        this.room = room;
    }

    public void restore() {
        Room room = this.room;
        room.setLength(length);
        room.setWidth(width);
    }

    public void printDescription() {
        System.out.println("Length: " + length + ", Width: " + width);
    }


}
