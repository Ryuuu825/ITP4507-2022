package com.BMS.Model;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

/**]
 * The Building class.
 *
 * @see com.BMS.Model.Factory.BuildingFactory
 * @see com.BMS.Model.Memento.CareTaker
 * @see com.BMS.Model.Memento.Memento
 */
public abstract class Building {
    private final int id;
    private final java.util.ArrayList<Room> rooms;

    public final static HashMap<Character, String> buildingType = new HashMap<>();

    // automatically receive the list of building types
    static {
        File file = new File("com/BMS/Model/Factory");
        for (File f : Objects.requireNonNull(file.listFiles())) {
            String name = f.getName();
            if (!name.contains("Building")) {
                buildingType.put(((char) (name.charAt(0) + 32)), name.replace(".java", "").replace("Factory", ""));
            }
        }
    }

    public Building(int id, int noOfRooms) {
        this.id = id;
        rooms = new java.util.ArrayList<>(noOfRooms);
    }

    public int getId() {
        return id;
    }

    public java.util.AbstractList<Room> getRooms() {
        return rooms;
    }

    public Room addRoom(double length, double width) {
        Room room = new Room(length, width);
        rooms.add(room);

        return room;
    }

    public void modifyRoom(int index, double length, double width) {
        Room room = rooms.get(index-1);
        room.setLength(length);
        room.setWidth(width);
    }

    public void deleteRoom(int index) {
        rooms.remove(index);
    }

    public void printRooms() {
        for (Room room : rooms) {
            System.out.println("Room No.: " + (rooms.indexOf(room) + 1) + ", " + room + "\n");
        }
    }

    public int getRoomQty() {
        return rooms.size();
    }

    public abstract void modifyBuilding();

    public abstract void printBuilding();


}