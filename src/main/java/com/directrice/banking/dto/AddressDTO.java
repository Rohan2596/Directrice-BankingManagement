package com.directrice.banking.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressDTO {

    private String address;
    private String town;
    private String district;
    private String state;
    private String country;
    private String postalCode;

}
