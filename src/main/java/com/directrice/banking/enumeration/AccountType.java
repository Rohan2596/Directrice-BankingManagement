package com.directrice.banking.enumeration;

public enum AccountType {

    SAVING_ACCOUNT("Saving Account."), CHECKING_ACCOUNT("Checking Account.");
    private String typeInfo;
    AccountType(String typeInfo) {
        this.typeInfo=typeInfo;
    }
}

