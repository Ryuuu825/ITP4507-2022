package com.BMS.Exception;

/**
 * This exception is thrown when the class of a object is not the expected one.
 */
public class ClassMismatchException extends BMSCustException {
    public ClassMismatchException(String expectedClass, String actualClass) {
        super("The expected class is " + expectedClass + " but the actual class is " + actualClass);
    }
}
