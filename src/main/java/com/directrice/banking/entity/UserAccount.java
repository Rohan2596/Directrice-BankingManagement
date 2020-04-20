package com.directrice.banking.entity;


import com.directrice.banking.dto.UserAccountDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserAccount {


    @Id
    private String userAccountId;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String nationality;
    private String countryOfResidence;
    private String occupation;
    private String accountNumber= UUID.randomUUID().toString();

    public UserAccount(UserAccountDTO userAccountDTO) {
        this.firstName=userAccountDTO.getFirstName();
        this.lastName=userAccountDTO.getLastName();
        this.birthDay=userAccountDTO.getBirthDay();
        this.nationality=userAccountDTO.getNationality();
        this.countryOfResidence=userAccountDTO.getCountryOfResidence();
        this.occupation=userAccountDTO.getOccupation();
    }

    @DBRef
    private Address address;
    @DBRef
    private KycDetails kycDetails;
}
