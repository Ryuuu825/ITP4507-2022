package com.BMS.Model.Factory;

import com.BMS.Model.*;
import com.BMS.Utils.CIN;

public class HouseFactory extends BuildingFactory {
    public Building createBuilding() {
        System.out.print("Building No. : ");
        int id = CIN.nextInt();

        System.out.print("No. of Floors  : ");
        int noOfFloors = CIN.nextInt();

        System.out.print("No. Rooms : ");
        int noOfRooms = CIN.nextInt();
        
        House house = new House(id, noOfRooms, noOfFloors);
        for (int i = 0; i < noOfRooms; i++) {
            System.out.println("Room No. : " + (i + 1) );
            System.out.print("Length : ");
            int length = CIN.nextInt();
            System.out.print("Width : ");
            int width = CIN.nextInt();
            house.addRoom(length, width);
        }

        return house;
    }

}