package com.directrice.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAccountDTO {

    private String firstName;
    private String lastName;
    private AddressDTO addressDTO;
    private String birthDay;
    private String nationality;
    private String countryOfResidence;
    private String occupation;
}
