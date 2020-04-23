package com.directrice.banking.supportService;

import com.directrice.banking.dto.UserSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    public String getUserId(String token){
        RestTemplate restTemplate=new RestTemplate();
        UserSummary userSummary=restTemplate.getForObject("http://localhost:8081/directrice/user/userId?token=" +token,UserSummary.class);
        return userSummary.getUserId();
    }
}
