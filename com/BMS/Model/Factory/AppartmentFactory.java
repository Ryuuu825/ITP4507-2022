package com.BMS.Model.Factory;

import com.BMS.Model.Appartment;
import com.BMS.Model.Building;
import com.BMS.Utils.CIN;

/**
 * The factory for Appartment.
 *
 * @see com.BMS.Model.Appartment
 */
public class AppartmentFactory extends BuildingFactory {
    public Building createBuilding() {
        System.out.print("Building No. : ");
        int id = CIN.nextInt();
        System.out.print("Monthly Rental : ");
        double monthlyRental = CIN.nextDouble();

        CIN.nextLine();

        System.out.print("Support Staff : ");
        String supportStaff = CIN.nextLine();

        System.out.print("No. Rooms : ");
        int noOfRooms = CIN.nextInt();


        Appartment appartment = new Appartment(id, noOfRooms, monthlyRental, supportStaff);
        for (int i = 0; i < noOfRooms; i++) {
            System.out.println("Room No. : " + (i + 1));
            System.out.print("Length : ");
            int length = CIN.nextInt();
            System.out.print("Width : ");
            int width = CIN.nextInt();
            appartment.addRoom(length, width);
        }

        return appartment;
    }
}