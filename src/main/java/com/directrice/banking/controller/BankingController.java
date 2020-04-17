package com.directrice.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directrice/banking")
public class BankingController {


    @PostMapping
    public ResponseEntity<String> createAccount(){
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
