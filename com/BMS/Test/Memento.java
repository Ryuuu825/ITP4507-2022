package com.BMS.Test;

import com.BMS.Model.Appartment;
import com.BMS.Model.House;
import com.BMS.Model.Memento.BMSCareTaker;
import com.BMS.Model.Memento.CareTaker;

public class Memento {

    public static void main(String[] arg) {

        // test the memento pattern

        // int id, int noOfRooms,
        // double monthlyRental, String supportStaff
        Appartment appartment = new Appartment(1, 2, 1000, "John");
        CareTaker careTaker = new BMSCareTaker();
        com.BMS.Model.Memento.Memento m;
        com.BMS.Model.Memento.Memento m1;
        System.out.println("Saving apartment state");
        m = careTaker.save(appartment);
        System.out.println(appartment);
        appartment.setMonthlyRental(2000);
        appartment.setSupportStaff("Jack");
        System.out.println(appartment);
        System.out.println("Restoring apartment state");
        careTaker.undo(m);
        System.out.println(appartment);

        House house = new House(2, 3, 2000);
        System.out.println("Saving house state");
        m1 = careTaker.save(house);
        System.out.println(house);
        house.setNoOfFloors(2);
        System.out.println(house);
        System.out.println("Restoring house state");
        careTaker.undo(m1);
        System.out.println(house);

        
    }
    
}
