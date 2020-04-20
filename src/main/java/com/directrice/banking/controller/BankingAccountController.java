package com.directrice.banking.controller;

import com.directrice.banking.dto.*;
import com.directrice.banking.response.Response;
import com.directrice.banking.service.BankingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/directrice/banking")
public class BankingAccountController {

    @Autowired
    private BankingAccountService bankingAccountService;

    @PostMapping("/user/account")
    public ResponseEntity<Response> createUserAccount(@RequestHeader String token,
                                                      @RequestBody @Valid UserAccountDTO userAccountDTO,
                                                      BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        bankingAccountService.addUserAccount(userAccountDTO);
        return new ResponseEntity<Response>(new Response(userAccountDTO.toString(),"",LocalDateTime.now().toString()), HttpStatus.CREATED);
    }

    @PutMapping("/user/account")
    public ResponseEntity<Response> updateUserAccount(@RequestHeader String token,
                                                      @RequestHeader String accountId,
                                                      @Valid @RequestBody UserAccountDTO userAccountDTO,
                                                      BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);        }
        return new ResponseEntity<>(new Response(), HttpStatus.OK);
    }

    @GetMapping("/user/{accountId}")
    public ResponseEntity<Response> getUserAccount(@RequestHeader String token,
                                                 @PathVariable String accountId){
        return new ResponseEntity<>(new Response(), HttpStatus.OK);
    }

    @GetMapping("/user/accounts")
    public ResponseEntity<Response> getAllUserAccount(){
        return new ResponseEntity<>(new Response(),HttpStatus.OK);
    }

    //Organisation

    @PostMapping("/organisation/account")
    public ResponseEntity<Response> createOrganisationAccount(@RequestHeader String token,
                                                            @Valid @RequestBody OrganisationDTO organisationDTO,
                                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Response(), HttpStatus.CREATED);
    }

    @PutMapping("/organisation/account")
    public ResponseEntity<Response> editOrganisationAccount(@RequestHeader String token,
                                                          @RequestHeader String accountId,
                                                          @Valid @RequestBody OrganisationDTO organisationDTO,
                                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Response(), HttpStatus.OK);
    }

    @GetMapping("/organisation/{accountId}")
    public ResponseEntity<Response> getOrganisationAccount(@RequestHeader String token,
                                                         @PathVariable String accountId){
        return new ResponseEntity<>(new Response(),HttpStatus.OK);
    }

    @GetMapping("/organisation/accounts")
    public ResponseEntity<Response> getAllOrganisationAccount(){
        return new ResponseEntity<>(new Response(),HttpStatus.OK);
    }

    @PostMapping("/kyc/upload")
    public ResponseEntity<Response>addKYCDetails(@RequestHeader String token,
                                                 @RequestHeader String accountId,
                                                 @RequestParam("file") MultipartFile multipartFile){
            if (multipartFile.getOriginalFilename().equals("") || multipartFile.getSize() == 0)
                return new ResponseEntity<>(new Response(), HttpStatus.BAD_REQUEST);
            if (multipartFile.getContentType().equals("image/png")
                    || multipartFile.getContentType().equals("image/jpg")
                    || multipartFile.getContentType().equals("image/jpeg"))
                return new ResponseEntity<>(new Response(), HttpStatus.OK);
            return new ResponseEntity<Response>(new Response(), HttpStatus.BAD_REQUEST);

        }

}
