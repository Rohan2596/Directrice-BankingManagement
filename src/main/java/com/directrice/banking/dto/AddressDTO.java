package com.directrice.banking.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressDTO {

    @NotNull(message = "Address pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z0-9'\\-\\s\\,]{2,50}",message = "Address pattern doesn't match.")
    private String address;

    @NotNull(message = "Town pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z0-9'\\-\\s\\,]{2,16}",message = "Town pattern doesn't match.")
    private String town;

    @NotNull(message = "District pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z0-9'\\-\\s\\,]{2,16}",message = "District pattern doesn't match.")
    private String district;

    @NotNull(message = "State pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z0-9'\\-\\s\\,]{2,16}",message = "State pattern doesn't match.")
    private String state;

    @NotNull(message = "Country pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z0-9'\\-\\s\\,]{2,16}",message = "Country pattern doesn't match.")
    private String country;

    @NotNull(message = "Postal Code pattern doesn't match.")
    @Pattern(regexp = "^[0-9]{6}",message = "Postal Code pattern doesn't match.")
    private String postalCode;

}
