package com.directrice.banking.entity;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.enumeration.AccountType;
import com.directrice.banking.enumeration.UserType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Document(collection = "organisation_account")
public class OrganisationAccount {


    @Id
    private String id;
    private String name;
    private String licenseNumber;
    private String website;
    private String type;
    private AddressDTO address;
    private String  registrationDate;
    private String accountNo;
    private String userType=UserType.LEGAL.name();
    private String accountType= AccountType.CURRENT_ACCOUNT.name();

    @DBRef
    private Address legalUserAddress;

    /*
    * 1)KYC deatils of orgainsation
    * 2)KYC deatils of legal user.
    * */
    @DBRef
    private List<KycDetails> kycDetailsList;

}
