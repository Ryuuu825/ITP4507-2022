package com.BMS.Exception;

/**
 * The base class for all exceptions.
 */
public abstract class BMSCustException extends RuntimeException {

    public BMSCustException(String message) {
        super(message);
    }

}
