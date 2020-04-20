package com.directrice.banking.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kyc_details")
public class KycDetails {

    @Id
    private String kycId;
    private String panCardUrl;
    private String aadharCardUrl;
}
