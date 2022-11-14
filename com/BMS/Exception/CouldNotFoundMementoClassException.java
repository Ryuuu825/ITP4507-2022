package com.BMS.Exception;

public class CouldNotFoundMementoClassException extends BMSCustException {
    public CouldNotFoundMementoClassException(String className) {
        super("The memento class of " + className + " could not be found");
    }
}
