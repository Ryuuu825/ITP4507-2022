package com.BMS.Test;

import com.BMS.Model.Factory.*;
import com.BMS.Model.*;

public class ModelFactory {
    public static void main(String[] argv) {
        
        BuildingFactory bf = new HouseFactory();
        Building b = bf.createBuilding();
        System.out.println(b);

        bf = new AppartmentFactory();
        b = bf.createBuilding();

        System.out.println(b);
    }
}
