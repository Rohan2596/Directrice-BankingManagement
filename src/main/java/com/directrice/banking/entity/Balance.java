package com.directrice.banking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "balance")
public class Balance {

    @Id
    private String id;
    private String userId;
    private String accountNo;
    private String currency;
    private float amount;
}
