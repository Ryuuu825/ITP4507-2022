package com.BMS.Exception;

public class ClassMismatchException extends BMSCustException {
    public ClassMismatchException(String expectedClass , String actualClass) {
        super("The expected class is " + expectedClass + " but the actual class is " + actualClass);
    }
}
