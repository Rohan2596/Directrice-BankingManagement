package com.directrice.banking.exception;

public class BankingException extends RuntimeException {

    public ExceptionTypes exceptionTypes;

    public BankingException(String message, ExceptionTypes exceptionTypes) {
        super(message);
        this.exceptionTypes = exceptionTypes;
    }

    public enum ExceptionTypes{
        INVALID_ACCOUNT_ID("Invalid Account Id.");

        public String errorMessage;
        ExceptionTypes(String errorMessage) {
            this.errorMessage=errorMessage;
        }
    }
}
