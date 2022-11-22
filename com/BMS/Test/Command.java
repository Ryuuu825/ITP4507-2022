package com.BMS.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import com.BMS.Command.BMSCommandManager;
import com.BMS.Model.Building;

public class Command {

    /**
     * @param argv
     */
    public static void main(String[] argv) {
        List<Building> buildings = new ArrayList<>();
        BMSCommandManager manager = new BMSCommandManager(buildings);

        // could not insert data into the input stream

        System.out.println("Executing command 'a'");
        manager.executeCommand('a');
        System.out.println("Executing command 'a'");
        manager.executeCommand('a');
        System.out.println("Executing command 'a'");
        manager.executeCommand('a');

        System.out.println("Executing command 'd' - 1001");
        manager.executeCommand('d');
        System.out.println("Executing command 'd' - 2100 ");
        manager.executeCommand('d');
        System.out.println("Executing command 'd' - display all");
        manager.executeCommand('d');

        System.out.println("Executing command 'm'");
        manager.executeCommand('m');
        System.out.println("Executing command 'm'");
        manager.executeCommand('m');

        System.out.println("Executing command 'e' , 'a'");
        manager.executeCommand('e');
        System.out.println("Executing command 'e' , 'm'"); 
        manager.executeCommand('e');
        System.out.println("Executing command 'e' , 'd'");
        manager.executeCommand('e');


        System.out.println("Executing command 'l'");
        manager.executeCommand('l');
        
        System.out.println("Executing command 'u'");
        manager.executeCommand('u');
        manager.executeCommand('u');
        manager.executeCommand('u');
        manager.executeCommand('l');
        manager.executeCommand('d');
        manager.executeCommand('u');
        manager.executeCommand('u');
        manager.executeCommand('u');
        manager.executeCommand('l');
        manager.executeCommand('d');


        System.out.println("Executing command 'r'");
        manager.executeCommand('r');
        manager.executeCommand('r');
        manager.executeCommand('r');
        manager.executeCommand('l');
        manager.executeCommand('d');


        

    }
}
