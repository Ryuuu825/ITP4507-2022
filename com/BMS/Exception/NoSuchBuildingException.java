package com.BMS.Exception;

public class NoSuchBuildingException extends RuntimeException {
    public NoSuchBuildingException() {
        super("There is no such building in the system");
    }
    
}
