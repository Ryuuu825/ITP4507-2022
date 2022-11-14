package com.BMS.Model;

import com.BMS.Utils.CIN;

public class House extends Building {
    private int noOfFloors;

    public House(int id , int noOfRooms,
            int noOfFloors) {
        super(id, noOfRooms);
        this.noOfFloors = noOfFloors;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }


    public void modifyBuilding() {
        System.out.print("No. of Floors : ");
        this.noOfFloors = CIN.nextInt();
        System.out.println("Building Modified:");
        printBuilding();
        System.out.println();
    }
    
    public void printBuilding() {
        System.out.println("Building No: " + getId() + ", No of Floors: " + getNoOfFloors()  );
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Building No: " + getId() + "\n");
        sb.append("No of Floors: " + getNoOfFloors() + "\n");
        for (Room room : getRooms()) {
            sb.append("Room No.: " + (getRooms().indexOf(room) + 1) + ", " + room + "\n");
        }
        return sb.append("\n").toString();
    }
    
}
