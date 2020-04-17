package com.directrice.banking.enumeration;

public enum UserType {
    NATURAL("Single User Account."),LEGAL("Organisation Account.");
private String typeInfo;
    UserType(String typeInfo) {
        this.typeInfo=typeInfo;
    }
}
