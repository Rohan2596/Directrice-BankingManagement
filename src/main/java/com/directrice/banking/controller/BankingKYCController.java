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
@RequestMapping("/directrice/banking")
public class BankingKYCController {

    @Autowired
    private AccountKYCServiceImpl accountKYCService;


    @PostMapping("/kyc/upload")
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

    @PutMapping("/kyc/upload")
    public ResponseEntity<Response> editKYCDetails(@RequestHeader String token,
                                                  @RequestHeader String accountNumber,
                                                  @RequestParam("file") MultipartFile multipartFile){
        if (multipartFile.getOriginalFilename().equals("") || multipartFile.getSize() == 0)
            return new ResponseEntity<>(new Response(), HttpStatus.BAD_REQUEST);
        if (multipartFile.getContentType().equals("image/png")
                || multipartFile.getContentType().equals("image/jpg")
                || multipartFile.getContentType().equals("image/jpeg"))
            return new ResponseEntity<>(new Response("", accountKYCService.editUserKycDetails(token, accountNumber, multipartFile), LocalDateTime.now().toString()), HttpStatus.OK);
        return new ResponseEntity<Response>(new Response(), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/kyc/{token}/{accountNumber}")
    public ResponseEntity<Response> getUserKycDetailsList(@PathVariable String token,
                                                          @PathVariable String accountNumber){
               return new ResponseEntity<Response>(new Response(), HttpStatus.OK);

    }

    @GetMapping("/kyc/all")
    public ResponseEntity<Response>getAllKYCDetails(){
        return new ResponseEntity<Response>(new Response(), HttpStatus.OK);
    }

}
