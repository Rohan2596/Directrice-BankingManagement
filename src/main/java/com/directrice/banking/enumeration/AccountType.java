package com.directrice.banking.enumeration;

public enum AccountType {

    SAVING_ACCOUNT("Saving Account."),CURRENT_ACCOUNT("Current Account.");
    private String typeInfo;
    AccountType(String typeInfo) {
        this.typeInfo=typeInfo;
    }
}

