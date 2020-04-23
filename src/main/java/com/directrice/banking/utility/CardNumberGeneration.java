package com.directrice.banking.utility;

import com.directrice.banking.dto.UserAccountDTO;

import java.time.LocalDateTime;
import java.util.Random;

public class CardNumberGeneration {

    private String randomMix(int outbound,int inbound){
        Random random=new Random();
        int number=random.nextInt(outbound-inbound)+10;
        return  String.valueOf(number);
    }
    //debitCard 16 digits
    private String debitCardGeneration(UserAccountDTO userAccountDTO){
        // create a LocalDateTime Object
        LocalDateTime local = LocalDateTime.parse(LocalDateTime.now().toString());
        // get Year
        int year = local.getYear();
        int day=local.getDayOfMonth();
        String firstSet=String.valueOf(year) + String.valueOf(day);
        String secondSet="7895";
        String thirdSet=randomMix(9999,1000);
        String fourset=randomMix(9999,1000);

        String debitCardNumber=firstSet+secondSet+thirdSet+fourset;
        return debitCardNumber;
    }

    //creditCard 18 digits
    private String creditCardGeneration(){
        return null;
    }
}
