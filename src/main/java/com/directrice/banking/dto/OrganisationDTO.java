package com.directrice.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrganisationDTO {

    @NotEmpty(message = "Organisation Name cannot be empty." )
    @NotNull(message = "Organisation Name cannot be empty." )
    private String name;

    @NotNull(message = "Organisation licenseNumber cannot be empty." )
    @Length(min = 4 ,message = "Organisation licenseNumber length should be greater than 4.")
    private String licenseNumber;


    private String website;

    @NotNull(message = "Organisation type cannot be empty." )
    @Length(min = 2 ,message = "Organisation type length should be greater than 2.")
    private String businessType;

    @Valid
    private AddressDTO address;

    @NotEmpty(message = "Organisation registrationDate cannot be empty." )
    @NotNull(message = "Organisation registrationDate cannot be empty." )
    private String  registrationDate;

}
