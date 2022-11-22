package com.BMS.Command;

import com.BMS.Command.Common.*;
import com.BMS.Command.Factory.EditRoomCmdFactory;
import com.BMS.Command.Factory.Factory;
import com.BMS.Command.Factory.Meta;
import com.BMS.Exception.BMSCustException;
import com.BMS.Exception.NoSuchBuildingException;
import com.BMS.Exception.NoSuchRoomException;
import com.BMS.Model.Building;
import com.BMS.Model.Memento.BMSCareTaker;
import com.BMS.Model.Room;
import com.BMS.Model.RoomMemento;
import com.BMS.Utils.CIN;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The command for editing rooms.
 * Also, a command manager for all room editing sub-commands.
 *
 * @see com.BMS.Command.EditRoomCmd.EditRoom_AddRoomCmd
 * @see com.BMS.Command.EditRoomCmd.EditRoom_DeleteRoomCmd
 * @see com.BMS.Command.EditRoomCmd.EditRoom_ModifyRoomCmd
 */
public class EditRoomCmd extends UndoableCommandBase implements CommandManager {

    /**
     * The command maps for all sub-commands.
     *
     * @see com.BMS.Command.EditRoomCmd.EditRoom_AddRoomCmd
     * @see com.BMS.Command.EditRoomCmd.EditRoom_DeleteRoomCmd
     * @see com.BMS.Command.EditRoomCmd.EditRoom_ModifyRoomCmd
     */
    private static final Map<Character, CmdMetaInfo> commandsMap = new LinkedHashMap<>();

    static {
        try {
            Map<Character, Meta> temp = EditRoomCmdFactory.EditRoomCmdGroupFactory.CommandFactoriesMap;

            for (Character key : temp.keySet()) {
                Meta v = temp.get(key);
                Factory cf = (Factory) Class.forName(v.factoryClassLocation).newInstance();
                commandsMap.put(key, new CmdMetaInfo(v.desc, cf));
            }
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

            System.err.println("Error: Cannot create instance of EditRoomCmdGroupFactory");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The sub-command executed in this instance
     */
    private EditRoom_CommandBase command;

    public EditRoomCmd(List<Building> buildings) {
        super(buildings);
    }

    public void execute() {
        System.out.print("Building No. : ");
        int buildingNo = CIN.nextInt();
        Building b = null;
        for (Building building : buildings) {
            if (building.getId() == (buildingNo)) {
                b = building;
                break;
            }
        }

        if (b == null) {
            throw new NoSuchBuildingException();
        }
        b.printBuilding();

        printAvailableCommands();
        char cmd = CIN.next().charAt(0);

        try {
            EditRoomCmdFactory.EditRoomCmdGroupFactory cf = (EditRoomCmdFactory.EditRoomCmdGroupFactory) commandsMap.get(cmd).getFactory();
            cf.setArgs(b);
            command = (EditRoom_CommandBase) cf.createCommand();
            command.execute();

        } catch (NullPointerException e) {
            System.err.println("Error: Invalid command");
            return;
        } catch (BMSCustException e) {
            System.err.println(e.getMessage());
            return;
        }

        
    }

    public void undo() {
        command.undo();
    }

    public void redo() {
        command.redo();
    }

    public void printDescription() {
        command.printDescription();
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

    public void executeCommand(char cmd) {
        execute();
    }

    /**
     * The base class for all edit-room sub-commands.
     */
    public static abstract class EditRoom_CommandBase implements UndoableCommand {

        protected Building building;

        protected Room room;
        protected int roomId;

        public EditRoom_CommandBase(Building building) {
            this.building = building;
        }

        public void execute() {

            // Updated Building:
            // Building No: 1001
            // Support Staff: John Chan
            // Monthly Rental: 18000.0
            // Room No.: 1, Length: 15.0, Width: 20.0
            // Room No.: 2, Length: 9.0, Width: 18.0
            // Room No.: 3, Length: 12.0, Width: 14.0

            System.out.println("Updated Building:");
            building.printBuilding();

        }

    }

    /**
     * The command for adding a room.
     */
    public static class EditRoom_AddRoomCmd extends EditRoom_CommandBase {

        public EditRoom_AddRoomCmd(Building building) {
            super(building);
        }

        public void execute() {
            System.out.print("Length : ");
            int length = CIN.nextInt();
            System.out.print("Width : ");
            int width = CIN.nextInt();
            room = building.addRoom(length, width);
            roomId = building.getRoomQty();
            super.execute();
        }

        public void undo() {
            building.getRooms().remove(room);
        }

        public void redo() {
            room = building.addRoom(room.getLength(), room.getWidth());
        }

        public void printDescription() {
            System.out.print("Add Room : ");
            System.out.printf("Building No. %d, Room No. %d, ", building.getId(), roomId);
            System.out.println(room);
        }
    }

    /**
     * The command for deleting a room.
     */
    public static class EditRoom_DeleteRoomCmd extends EditRoom_CommandBase {

        public EditRoom_DeleteRoomCmd(Building building) {
            super(building);
        }

        public void execute() {
            System.out.print("Room No. : ");
            roomId = CIN.nextInt();
            try {
                room = building.getRooms().get(roomId - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchRoomException();
            }
            building.getRooms().remove(room);
            super.execute();
        }

        public void undo() {
            building.getRooms().add(roomId - 1, room);
        }

        public void redo() {
            building.getRooms().remove(room);
        }

        public void printDescription() {
            System.out.print("Delete Room : ");
            System.out.printf("Building No. %d, Room No. %d, ", building.getId(), roomId);
            System.out.println(room);
        }


    }

    /**
     * The command for changing the length of a room.
     */
    public static class EditRoom_ModifyRoomCmd extends EditRoom_CommandBase {

        private RoomMemento oldRoomMemento;
        private RoomMemento newRoomMemento;
        private CmdStatus status;

        public EditRoom_ModifyRoomCmd(Building building) {
            super(building);
            this.status = CmdStatus.PENDING;
        }

        public void execute() {
            System.out.print("Room No. : ");
            roomId = CIN.nextInt();
            try {
                room = building.getRooms().get(roomId - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchRoomException();
            }
            try {
                oldRoomMemento = (RoomMemento) BMSCareTaker.instance.save(room);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("Length : ");
            double length = CIN.nextInt();
            System.out.print("Width : ");
            double width = CIN.nextInt();

            building.modifyRoom(roomId, length, width);

            try {
                newRoomMemento = (RoomMemento) BMSCareTaker.instance.save(room);
            } catch (Exception e) {
                e.printStackTrace();
            }
            status = CmdStatus.EXECUTED;
        }

        public void undo() {
            try {
                BMSCareTaker.instance.undo(oldRoomMemento);
            } catch (BMSCustException e) {
                e.printStackTrace();
            }
            this.status = CmdStatus.UNDONE;
        }

        public void redo() {
            try {
                BMSCareTaker.instance.undo(newRoomMemento);
            } catch (BMSCustException e) {
                e.printStackTrace();
            }
            this.status = CmdStatus.REDONE;
        }

        public void printDescription() {
            System.out.print("Modify Room : ");
            System.out.printf("Building No. %d, Room No. %d, ", building.getId(), roomId);
            newRoomMemento.printDescription();
            }
        }

    }
