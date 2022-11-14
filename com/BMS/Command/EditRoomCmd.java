package com.BMS.Command;

import java.util.*;

import com.BMS.Command.Common.*;
import com.BMS.Command.Factory.EditBuildingCmdFactory;
import com.BMS.Command.Factory.Factory;
import com.BMS.Command.Factory.Meta;
import com.BMS.Model.Building;
import com.BMS.Model.Room;
import com.BMS.Model.RoomMemento;
import com.BMS.Model.Memento.BMSCareTaker;
import com.BMS.Utils.*;
import com.BMS.Exception.*;

public class EditRoomCmd extends UndoableCommandBase implements CommandManager {

    private static Map<Character, CmdMetaInfo> commandsMap = new LinkedHashMap<Character, CmdMetaInfo>();
    static {
        try {
            Map<Character, Meta> temp =  EditBuildingCmdFactory.EditRoomCmdGroupFactory.CommandFactoriesMap;

            for (Character key : temp.keySet()) {
                Character k = key;
                Meta v = temp.get(key);
                Factory cf = (Factory) Class.forName( v.factoryClassLocation ).newInstance();
                commandsMap.put(k, new CmdMetaInfo(v.desc, cf));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    

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
        System.out.println(b.toString());

        printAvailableCommands();
        char cmd = CIN.next().charAt(0);

        EditBuildingCmdFactory.EditRoomCmdGroupFactory cf = (EditBuildingCmdFactory.EditRoomCmdGroupFactory) commandsMap.get(cmd).getFactory();
        cf.setArgs(b);
        command = (EditRoom_CommandBase ) cf.createCommand();
        command.execute();
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

    public void printAvailableCommands()
    {
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
    public void executeCommand(char cmd)
    {
        execute();
    }

    public static class EditRoom_CommandBase implements UndoableCommand {

        protected Building building;
        protected CmdStatus status;

        protected Room room;
        protected int roomId;
    
        public EditRoom_CommandBase(Building building) {
            this.building = building;
            this.status = CmdStatus.PENDING;
        }
    
        public void execute() {
            status = CmdStatus.EXECUTED;
    
            // Updated Building:
            // Building No: 1001
            // Support Staff: John Chan
            // Monthly Rental: 18000.0
            // Room No.: 1, Length: 15.0, Width: 20.0
            // Room No.: 2, Length: 9.0, Width: 18.0
            // Room No.: 3, Length: 12.0, Width: 14.0
    
            System.out.println("Updated Building:");
            System.out.println(building); 
    
        }
    
        public void undo() {
            status = CmdStatus.UNDONE;
        }
    
        public void redo() {
            status = CmdStatus.REDONE;
        }
    
        public void printDescription() {
            return;
        }
    }

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
            super.undo();
        }
    
        public void redo() {
            room = building.addRoom(room.getLength(), room.getWidth());
            super.redo();
        }

        public void printDescription() {
            System.out.print("Add Room : " );
            System.out.printf("Building No. %d, Room No. %d, ", building.getId(), roomId);
            System.out.println(room);
        }
    }

    public static class EditRoom_DeleteRoomCmd extends EditRoom_CommandBase {

        public EditRoom_DeleteRoomCmd(Building building) {
            super(building);
        }
    
        public void execute() {
            System.out.print("Room No. : ");
            roomId = CIN.nextInt();
            room = building.getRooms().get(roomId - 1);
            building.getRooms().remove(room);
            super.execute();
        }
    
        public void undo() {
            building.getRooms().add(roomId - 1, room);
            super.undo();
        }
    
        public void redo() {
            building.getRooms().remove(room);
            super.redo();
        }

        public void printDescription() {
            System.out.print("Delete Room : " );
            System.out.printf("Building No. %d, Room No. %d, ", building.getId(), roomId);
            System.out.println(room);
        }

        
    
    }

    public static class EditRoom_ModifyRoomCmd extends EditRoom_CommandBase {
            
            private RoomMemento oldRoomMemento;
            private RoomMemento newRoomMemento;
        
            public EditRoom_ModifyRoomCmd(Building building) {
                super(building);
            }
        
            public void execute() {
                System.out.print("Room No. : ");
                roomId = CIN.nextInt();
                room = building.getRooms().get(roomId - 1);
                try {
                    oldRoomMemento = (RoomMemento) BMSCareTaker.instance.save(room);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.print("Length : ");
                double length = CIN.nextInt();
                System.out.print("Width : ");
                double width = CIN.nextInt();
                room.setLength(length);
                room.setWidth(width);

                try {
                    newRoomMemento = (RoomMemento) BMSCareTaker.instance.save(room);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                super.execute();
            }
        
            public void undo() {
                try {
                    BMSCareTaker.instance.undo(oldRoomMemento);
                }catch (BMSCustException e) {
                    e.printStackTrace();
                }
                super.undo();
            }
        
            public void redo() {
                try {
                    BMSCareTaker.instance.undo(newRoomMemento);
                }catch (BMSCustException e) {
                    e.printStackTrace();
                }
                super.redo();
            }

            public void printDescription() {
                System.out.print("Modify Room : " );
                System.out.printf("Building No. %d, Room No. %d, ", building.getId(), roomId);
                newRoomMemento.printDescription();
            }
        
    }
}
