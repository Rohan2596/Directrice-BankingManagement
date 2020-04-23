package com.directrice.banking.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class UserCardDetails {

    @Id
    private String id;
    private String userId;
    private String userAccountNo;
    private String userName;
    private String cvvNo;
    private String cardType;
    private String cardNumber;
    private String cardIssuesDate;
    private String cardExpiryDate;
    private String cardPin;
}
