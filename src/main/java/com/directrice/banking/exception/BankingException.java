package com.directrice.banking.exception;

public class BankingException extends RuntimeException {

    public ExceptionTypes exceptionTypes;

    public BankingException(String message, ExceptionTypes exceptionTypes) {
        super(message);
        this.exceptionTypes = exceptionTypes;
    }

    public enum ExceptionTypes{
        ALREADY_ACCOUNT_CREATED("Account Already Created."),
        INVALID_USER_ID("Invalid User Id."),
        INVALID_ACCOUNT_NUMBER("Invalid Account Number.");

        public String errorMessage;
        ExceptionTypes(String errorMessage) {
            this.errorMessage=errorMessage;
        }
    }
}
