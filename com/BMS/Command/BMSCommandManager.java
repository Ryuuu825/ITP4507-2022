package com.BMS.Command;

import java.util.*;

import com.BMS.Command.Common.*;
import com.BMS.Command.Factory.*;
import com.BMS.Exception.BMSCustException;
import com.BMS.Model.Building;

public class BMSCommandManager implements CommandManager {

    private Stack<UndoableCommand> undoStack;
    private Stack<UndoableCommand> redoStack;
    private Map<Character, CmdMetaInfo> commandsMap = new LinkedHashMap<Character, CmdMetaInfo>();

    public BMSCommandManager(List< Building > buildings ) {
        undoStack = new Stack<UndoableCommand>();
        redoStack = new Stack<UndoableCommand>();

        try {
            for (Character key : com.BMS.Command.Factory.CmdFactoy.CommandFactoriesMap.keySet()) {
                Character k = key;
                Meta v = com.BMS.Command.Factory.CmdFactoy.CommandFactoriesMap.get(key);
                CmdFactoy cf = (CmdFactoy) Class.forName(v.factoryClassLocation).newInstance();
                cf.setArgs(buildings);
                commandsMap.put(k, new CmdMetaInfo(v.desc, cf));
            }

            commandsMap.put('u', new CmdMetaInfo("undo" , 
                new CmdFactoy() {
                    public Command createCommand() {
                        return new Command() {
                            public void execute() {
                                undo();
                            }
                        };
                    }
                }
            ));
            commandsMap.put('r', new CmdMetaInfo("redo" , 
                new CmdFactoy() {
                    public Command createCommand() {
                        return new Command() {
                            public void execute() {
                                redo();
                            }
                        };
                    }
                }
            ));
            commandsMap.put('l', new CmdMetaInfo("list undo/redo" , 
                new CmdFactoy() {
                    public Command createCommand() {
                        return new Command() {
                            public void execute() {
                                printUndoStack();
                                printRedoStack();
                            }
                        };
                    }
                }
            ));
            commandsMap.put('x', new CmdMetaInfo("exit system", (CmdFactoy) Class.forName("com.BMS.Command.Factory.ExitCmdFactory").newInstance()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAvailableCommands() {
        System.out.print("\nPlease enter command:[");
        Object[] cmdShortCutList = commandsMap.keySet().toArray();
        for (int i = 0; i < cmdShortCutList.length ; i++) {
            Character key = (Character) cmdShortCutList[i];
            if (i < cmdShortCutList.length - 1)
                System.out.printf("%c | ", key);
            else
                System.out.printf("%c]", key);
        }
        System.out.println();

        for (int i = 0; i < cmdShortCutList.length; i++) {
            Character key = (Character) cmdShortCutList[i];
            String desc =  commandsMap.get(key).desc();
            System.out.printf("%c = %s", key, desc);
            if (i < cmdShortCutList.length - 1)
                System.out.printf(" | ");
            else
                System.out.printf(" ");
        }
        System.out.println();
    }
    

    public void executeCommand(char cmd) {

        Command command = commandsMap.get(cmd).getFactory().createCommand();
        try {
            command.execute();
        }catch(BMSCustException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        if (command instanceof UndoableCommand) {
            undoStack.push((UndoableCommand)command);
        }
    }

    private void undo() {
        if (undoStack.empty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        UndoableCommand command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    }

    private void redo() {
        if (redoStack.empty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        UndoableCommand command = redoStack.pop();
        command.redo();
        undoStack.push(command);
    }

    private void printUndoStack() {
        System.out.println("Undo List:");
        
        if (undoStack.empty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        
        for (int i = undoStack.size() - 1; i >= 0; i--) 
           undoStack.get(i).printDescription();

        System.out.println();
    }

    private void printRedoStack() {
        System.out.println("Redo List:");

        if (redoStack.empty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        
        for (int i = redoStack.size() - 1; i >= 0; i--) 
            redoStack.get(i).printDescription();

        System.out.println();
    }

    




    
}
