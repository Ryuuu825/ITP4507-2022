package com.BMS.Exception;

/**
 * This exception is thrown when jvm cannot create an instance of a class.
 */
public class CouldNotCreateInstanceException extends BMSCustException {

    public CouldNotCreateInstanceException(String className) {
        super("Could not create instance of class: " + className);
    }
}
