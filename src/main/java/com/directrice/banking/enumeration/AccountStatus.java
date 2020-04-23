package com.directrice.banking.enumeration;

public enum AccountStatus {
    ACTIVE_ACCOUNT("Account is active."),INACTIVE_ACCOUNT("Account is inactive.");
    private String typeInfo;
    AccountStatus(String typeInfo) {
        this.typeInfo=typeInfo;
    }
}
