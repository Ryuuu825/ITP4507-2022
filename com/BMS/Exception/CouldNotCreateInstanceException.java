package com.BMS.Exception;

public class CouldNotCreateInstanceException extends BMSCustException {
    
    public CouldNotCreateInstanceException(String className)
    {
        super("Could not create instance of class: " + className);
    }
}
