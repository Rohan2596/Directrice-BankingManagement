package com.directrice.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAccountDTO {

    @NotNull(message = "FirstName pattern doesn't match.")
    @Pattern(regexp = "^[a-zA-Z]{2,16}",message = "FirstName pattern doesn't match.")
    private String firstName;

    @NotNull(message = "LastName pattern doesn't match.")
    @Pattern(regexp = "^[a-zA-Z]{4,16}" ,message = "LastName pattern doesn't match.")
    private String lastName;

    @NotNull(message = "Birth Date pattern doesn't match.")
    @NotEmpty(message = "Birth Date pattern doesn't match.")
    private String birthDay;

    @NotNull(message = "Nationality pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z]{4,20}" ,message = "Nationality pattern doesn't match.")
    private String nationality;

    @NotNull(message = "Country Of Residence pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z]{4,56}" ,message = "Country Of Residence pattern doesn't match.")
    private String countryOfResidence;

    @NotNull(message = "Occupation pattern doesn't match.")
    @Pattern(regexp = "^[A-Za-z\\-\\s]{2,16}" ,message = "Occupation pattern doesn't match.")
    private String occupation;




}
