package com.directrice.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LegalPersonDTO {

    private String firstName;
    private String lastName;
    private String birthDay;
    private String mobileNumber;
    private String personEmail;
    private AddressDTO legalPersonAddress;
    private String nationality;
    private String countryOfResidence;
    private String role;
}
