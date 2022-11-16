package com.BMS.Model;

/**
 * The Memento class for House.
 */
public class HouseMemento extends BuildingMemento {

    // int noOfFloors;
    protected int noOfFloors;

    public HouseMemento(House house) {
        super(house);
        this.noOfFloors = house.getNoOfFloors();
    }

    public void restore() {
        super.restore();
        House house = (House) building;
        house.setNoOfFloors(noOfFloors);
    }


}
