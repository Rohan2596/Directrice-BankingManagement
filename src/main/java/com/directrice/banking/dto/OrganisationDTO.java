package com.directrice.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrganisationDTO {

    private String organisationType;
    private String organisationName;
    private AddressDTO organisationAddress;
    private String licenseNumber;
    private LegalPersonDTO legalPersonDTO;
}
