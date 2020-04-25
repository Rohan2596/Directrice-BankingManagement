package com.directrice.banking.controller;

import com.directrice.banking.response.Response;
import com.directrice.banking.service.AccountKYCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/directrice/banking/kyc")
public class BankingKYCController {

    @Autowired
    private AccountKYCServiceImpl accountKYCService;


    @PostMapping("/upload")
    public ResponseEntity<Response> addKYCDetails(@RequestHeader String token,
                                                  @RequestHeader String accountNumber,
                                                  @RequestParam("file") MultipartFile multipartFile){
        if (multipartFile.getOriginalFilename().equals("") || multipartFile.getSize() == 0)
            return new ResponseEntity<>(new Response(), HttpStatus.BAD_REQUEST);
        if (multipartFile.getContentType().equals("image/png")
                || multipartFile.getContentType().equals("image/jpg")
                || multipartFile.getContentType().equals("image/jpeg"))
            return new ResponseEntity<>(new Response("", accountKYCService.addUserKycDetails(token, accountNumber, multipartFile), LocalDateTime.now().toString()), HttpStatus.OK);
        return new ResponseEntity<Response>(new Response(), HttpStatus.BAD_REQUEST);

    }



    @GetMapping("/{accountNumber}")
    public ResponseEntity<Response> getUserKycDetailsList(@RequestHeader String token,
                                                          @PathVariable String accountNumber){

               return new ResponseEntity<Response>(new Response("KYC",accountKYCService.getUserKycDetails(token,accountNumber),LocalDateTime.now().toString()), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<Response>getAllKYCDetails(){
        return new ResponseEntity<Response>(new Response("KYC List",accountKYCService.getAllUserKycDetails(),LocalDateTime.now().toString()), HttpStatus.OK);
    }

}
