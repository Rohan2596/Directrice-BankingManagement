package com.directrice.banking.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "balance")
@Setter
@Getter
public class Balance {

    @Id
    private String id;
    private String userId;
    private String accountNo;
    private String currency;
    private float amount;
}
