package com.BMS.Exception;

/**
 * This exception is thrown when user input is invalid, which the specified building id does not exist.
 */
public class NoSuchRoomException extends RuntimeException {
    public NoSuchRoomException() {
        super("There is no such room in the system");
    }

}
