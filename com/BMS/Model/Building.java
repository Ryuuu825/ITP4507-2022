package com.BMS.Model;

import java.io.File;
import java.util.*;


public abstract class Building {
    private int id;
    private java.util.ArrayList<Room> rooms;

    public static HashMap<Character,String> buildingType = new HashMap<Character,String>();
    // automatically receive the list of building types
    static {
        File file = new File("com/BMS/Model/Factory");
        for (File f : file.listFiles()) {
            String name = f.getName();
            if (! name.contains("Building")) {
                buildingType.put(( (char) (name.charAt(0) + 32)), name.replace(".java", "").replace("Factory", ""));
            }
        }
    }
    public Building(int id , int noOfRooms) {
        this.id = id;
        rooms = new java.util.ArrayList<Room>(noOfRooms);
    }

    public int getId() {
        return id;
    }
    
    public java.util.AbstractList<Room> getRooms() {
        return rooms;
    }

    public Room addRoom(double length , double width) {
        Room room = new Room(length, width);
        rooms.add(room);

        return room;
    }

    public void modifyRoom(int index, double length, double width) {
        // rooms.get(index).setLength(length);
        // rooms.get(index).setWidth(width);
    }

    public void deleteRoom(int index) {
        rooms.remove(index);
    }

    public void printRooms() {
        for (Room room : rooms) {
            System.out.println("Room No.: " + (rooms.indexOf(room) + 1) + ", " + room + "\n" );
        }
    }

    public int getRoomQty() {
        return rooms.size();
    }

    public abstract void modifyBuilding() ;
    public abstract void printBuilding() ;




}