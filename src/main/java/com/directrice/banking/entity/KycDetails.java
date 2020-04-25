package com.directrice.banking.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "kyc_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KycDetails {

    @Id
    private String kycId;
    private String kycImageURL;
    private String status;
    private String userId;
    private String accountNumber;

    public KycDetails(String kycImageURL, String status, String userId, String accountNumber) {
        this.kycImageURL = kycImageURL;
        this.status = status;
        this.userId = userId;
        this.accountNumber = accountNumber;
    }
}
