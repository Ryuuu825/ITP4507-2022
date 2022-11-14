package com.BMS.Test;

import java.util.*;
import java.io.*;

import com.BMS.Command.*;
import com.BMS.Command.Factory.*;
import com.BMS.Model.*;


public class TestCommandFactory {
    public static void main(String[] argv) {
        List<String> cmds = new ArrayList<String>();
        // automatically receive the list of building types
        File file = new File("com/BMS/Command/Factory");
        for (File f : file.listFiles()) {
            String name = f.getName();
            cmds.add(name.replace(".java", ""));
            System.out.println(name.replace(".java", ""));
        }

        cmds.remove(0);

        // add some dummy data for testing
        List<Building> buildings = new ArrayList<Building>();
        buildings.add(new House(1, 2,2));
        buildings.add(new Appartment(2, 3,2,"Ken"));
        buildings.add(new Appartment(3, 3,2,"Ken"));
        buildings.add(new Appartment(4, 3,2,"Ken"));
        buildings.add(new Appartment(5, 3,2,"Ken"));



        try {
            int cmd = new Scanner(System.in).nextInt();
            CmdFactoy cf =  (CmdFactoy) Class.forName("com.BMS.Command.Factory." + cmds.get(cmd) ).getDeclaredConstructor().newInstance();
            cf.setArgs(buildings);
            cf.createCommand().execute();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Invalid Command Type");
            return;
        }

    }
}
