package com.directrice.banking.entity;


import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.enumeration.AccountType;
import com.directrice.banking.enumeration.UserType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.UUID;

@Document
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserAccount {


    @Id
    private String userAccountId;
    private String userId;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String nationality;
    private String countryOfResidence;
    private String occupation;
    private String userType= UserType.NATURAL.name();
    private String accountNumber;
    private Boolean cardIssued=false;
    private String status;


    /*
    * Saving can be coverted into demant account/current account by increasing min 15000 amount balances
    * Along with income proof or tax details.
    *  */
    private String accountType= AccountType.SAVING_ACCOUNT.name();

    /*
     * Adding Address to user account
     * */
    @DBRef
    private Address address;
    /*
     * Adding KYCDetails to user account
     * */
    private String kycStatus;
    @DBRef
    private KycDetails kycDetails;


    /*
     * Adding different Cards to user account(min-1,max-2)
     * Assingning after kyc verification with one time pin which needed to be change within date of issue of 7 days.
     * */
    @DBRef
    private UserCardDetails userCardDetailsList;
}
