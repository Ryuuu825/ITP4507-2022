package com.BMS.Model;

import com.BMS.Utils.CIN;

/**
 * The House class.
 *
 * @see com.BMS.Model.Building
 */
public class House extends Building {
    private int noOfFloors;

    public House(int id, int noOfRooms,
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
        System.out.println("Building No: " + getId() + ", No of Floors: " + getNoOfFloors());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Building No: ").append(getId()).append("\n");
        sb.append("No of Floors: ").append(getNoOfFloors()).append("\n");
        for (Room room : getRooms()) {
            sb.append("Room No.: ").append(getRooms().indexOf(room) + 1).append(", ").append(room).append("\n");
        }
        return sb.append("\n").toString();
    }

}
