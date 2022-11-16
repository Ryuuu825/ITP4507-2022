package com.BMS.Exception;

/**
 * This exception is thrown when user input is invalid, which the specified building id does not exist.
 */
public class NoSuchBuildingException extends RuntimeException {
    public NoSuchBuildingException() {
        super("There is no such building in the system");
    }

}
