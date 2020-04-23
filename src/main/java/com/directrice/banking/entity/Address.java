package com.directrice.banking.entity;


import com.directrice.banking.dto.AddressDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "address")
@Getter
@Setter
public class Address {

    @Id
    private String id;
    private String userId;
    private String accountNo;
    private String address;
    private String town;
    private String district;
    private String state;
    private String country;
    private String postalCode;

}
