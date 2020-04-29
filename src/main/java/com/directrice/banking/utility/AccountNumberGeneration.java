package com.directrice.banking.utility;

import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class AccountNumberGeneration {

//11 digits number
    public String userAccountNumbers(UserAccountDTO userAccountDTO){
        // create a LocalDateTime Object
        LocalDateTime local = LocalDateTime.parse(LocalDateTime.now().toString());

            // get Year
        int year = local.getYear();
        String companyName="D" + year;
        String name= userAccountDTO.getLastName().toUpperCase().charAt(0) + userAccountDTO.getBirthDay().substring(0,2);
        String fourDigits=randomMix(9999,1000);
//        System.out.println(companyName+" company "+name + "name" + fourDigits +"digits");
        return companyName+name+fourDigits;
    }

    private String randomMix(int outbound,int inbound){
        Random random=new Random();
        int number=random.nextInt(outbound-inbound)+10;
        return  String.valueOf(number);
    }
    //14 digits number
    public String orgAccountNumbers(OrganisationDTO organisationDTO){
        // create a LocalDateTime Object
        LocalDateTime local = LocalDateTime.parse(LocalDateTime.now().toString());

        // get Year
        int year = local.getYear();
        String companyName="D" + year;
        String name= organisationDTO.getName().toUpperCase().charAt(0) + organisationDTO.getRegistrationDate().substring(0,4);
        String fourDigits=randomMix(9999,1000);
//        System.out.println(companyName+" company "+name + "name" + fourDigits +"digits");
        return companyName+name+fourDigits;
    }


    
    
}
