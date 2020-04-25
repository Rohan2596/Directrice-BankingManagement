package com.directrice.banking.enumeration;

public enum KYCStatus {

    APPROVED_STATE("KYC Details are verified."),
    PROCESSING_STATE("KYC Details are in processing State."),
    REJECTED_STATE("KYC Details are rejected.");
    private String typeInfo;
    KYCStatus(String typeInfo) {
        this.typeInfo=typeInfo;
    }
}
