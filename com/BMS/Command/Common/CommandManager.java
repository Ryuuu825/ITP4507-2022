package com.BMS.Command.Common;

/**
 * The interface for handler/invoker of the command pattern.
 */
public interface CommandManager {
    void printAvailableCommands();

    void executeCommand(char cmd);
}
