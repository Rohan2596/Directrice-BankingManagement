package com.directrice.banking.entity;


import com.directrice.banking.dto.AddressDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {

    @Id
    private String addressId;
    private String address;
    private String town;
    private String district;
    private String state;
    private String country;
    private String postalCode;


    public Address(AddressDTO addressDTO) {
        this.address=addressDTO.getAddress();
        this.town=addressDTO.getTown();
        this.district=addressDTO.getDistrict();
        this.state=addressDTO.getState();
        this.country=addressDTO.getCountry();
        this.postalCode=addressDTO.getPostalCode();
    }
}
