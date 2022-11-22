package com.BMS.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.BMS.Command.Common.CmdMetaInfo;
import com.BMS.Command.Factory.CmdFactoy;
import com.BMS.Command.Factory.Meta;
import com.BMS.Model.Appartment;
import com.BMS.Model.Building;
import com.BMS.Model.House;

public class CommandFactory {

    public static void main(String[] argv) {
        List<Building> buildings = new ArrayList<>();
        Appartment appartment = new Appartment(1, 2, 1000, "John");
        House house = new House(2, 3, 2000);
        buildings.add(appartment);
        buildings.add(house);
        Map<Character, CmdMetaInfo> commandsMap = new LinkedHashMap<>();
        // get all available commands
        try {
            for (Character key : com.BMS.Command.Factory.CmdFactoy.CommandFactoriesMap.keySet()) {
                Meta v = com.BMS.Command.Factory.CmdFactoy.CommandFactoriesMap.get(key);
                CmdFactoy cf = (CmdFactoy) Class.forName(v.factoryClassLocation).newInstance();
                cf.setArgs(buildings);
                commandsMap.put(key, new CmdMetaInfo(v.desc, cf));
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for  (Character key : commandsMap.keySet()) {
            System.out.println(key + " " + ((CmdMetaInfo)commandsMap.get(key)).desc());
        }
        
    }
    
}
