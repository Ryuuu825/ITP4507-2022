package com.BMS.Exception;

/**
 * This exception is thrown when user input is invalid.
 */
public class InvalidUserInputException extends BMSCustException {
    public InvalidUserInputException(String message) {
        super(message);
    }
}
