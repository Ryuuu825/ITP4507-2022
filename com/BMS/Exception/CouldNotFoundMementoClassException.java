package com.BMS.Exception;


/**
 * This exception is thrown when jvm cannot find the memento class of the object.
 */
public class CouldNotFoundMementoClassException extends BMSCustException {
    public CouldNotFoundMementoClassException(String className) {
        super("The memento class of " + className + " could not be found");
    }
}
