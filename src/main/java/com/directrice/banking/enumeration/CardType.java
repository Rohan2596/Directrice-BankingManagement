package com.directrice.banking.enumeration;

public enum CardType {
    DEBIT_CARD("Debit_Card"),CREDIT_CARD("Credit_Card");
    private String message;

    CardType(String message) {
        this.message = message;
    }
}
