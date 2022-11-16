package com.BMS.Command;

import com.BMS.Command.Common.CmdMetaInfo;
import com.BMS.Command.Common.Command;
import com.BMS.Command.Common.CommandManager;
import com.BMS.Command.Common.UndoableCommand;
import com.BMS.Command.Factory.CmdFactoy;
import com.BMS.Command.Factory.Meta;
import com.BMS.Exception.BMSCustException;
import com.BMS.Model.Building;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * The command manager for BMS.
 * It is holding the factory for all commands.
 *
 * @see com.BMS.Command.Factory.CmdFactoy
 */
public class BMSCommandManager implements CommandManager {

    private final Stack<UndoableCommand> undoStack;
    private final Stack<UndoableCommand> redoStack;
    private final Map<Character, CmdMetaInfo> commandsMap = new LinkedHashMap<>();

    public BMSCommandManager(List<Building> buildings) {
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        try {
            // get all available commands
            for (Character key : com.BMS.Command.Factory.CmdFactoy.CommandFactoriesMap.keySet()) {
                Meta v = com.BMS.Command.Factory.CmdFactoy.CommandFactoriesMap.get(key);
                CmdFactoy cf = (CmdFactoy) Class.forName(v.factoryClassLocation).newInstance();
                cf.setArgs(buildings);
                commandsMap.put(key, new CmdMetaInfo(v.desc, cf));
            }

            // manually add the undo and redo function and encapsulate them into a command instance
            commandsMap.put('u', new CmdMetaInfo("undo",
                    new CmdFactoy() {
                        public Command createCommand() {
                            return () -> undo();
                        }
                    }
            ));
            commandsMap.put('r', new CmdMetaInfo("redo",
                    new CmdFactoy() {
                        public Command createCommand() {
                            return () -> redo();
                        }
                    }
            ));
            commandsMap.put('l', new CmdMetaInfo("list undo/redo",
                    new CmdFactoy() {
                        public Command createCommand() {
                            return () -> {
                                printUndoStack();
                                printRedoStack();
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
        for (int i = 0; i < cmdShortCutList.length; i++) {
            Character key = (Character) cmdShortCutList[i];
            if (i < cmdShortCutList.length - 1)
                System.out.printf("%c | ", key);
            else
                System.out.printf("%c]", key);
        }
        System.out.println();

        for (int i = 0; i < cmdShortCutList.length; i++) {
            Character key = (Character) cmdShortCutList[i];
            String desc = commandsMap.get(key).desc();
            System.out.printf("%c = %s", key, desc);
            if (i < cmdShortCutList.length - 1)
                System.out.print(" | ");
            else
                System.out.print(" ");
        }
        System.out.println();
    }

    /**
     * Get the command's factory instance by the command shortcut,
     * then create the command instance and execute it.
     *
     * @param cmd the command shortcut, which specified in the command factory base class
     * @throws BMSCustException if the command shortcut is not valid
     */
    public void executeCommand(char cmd) {

        Command command = commandsMap.get(cmd).getFactory().createCommand();
        try {
            command.execute();
        } catch (BMSCustException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (command instanceof UndoableCommand) {
            undoStack.push((UndoableCommand) command);
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
