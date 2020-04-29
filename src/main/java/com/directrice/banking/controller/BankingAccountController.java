package com.directrice.banking.controller;

import com.directrice.banking.dto.*;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.response.Response;
import com.directrice.banking.service.AccountKYCServiceImpl;
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

    @Autowired
    private AccountKYCServiceImpl accountKYCService;

    @PostMapping("/user/account")
    public ResponseEntity<Response> createUserAccount(@RequestHeader String token,
                                                      @RequestBody @Valid UserAccountDTO userAccountDTO,
                                                      BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        UserAccount userAccount=bankingAccountService.addUserAccount(token,userAccountDTO);
        return new ResponseEntity<Response>(new Response(userAccountDTO.toString(),userAccount,LocalDateTime.now().toString()), HttpStatus.CREATED);
    }

    @PutMapping("/user/account")
    public ResponseEntity<Response> updateUserAccount(@RequestHeader String token,
                                                      @RequestHeader String accountNumber,
                                                      @Valid @RequestBody UserAccountDTO userAccountDTO,
                                                      BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);        }
        UserAccount userAccount=bankingAccountService.editUserAccount(token,accountNumber,userAccountDTO);
        return new ResponseEntity<>(new Response("Updating User Account.",userAccount,LocalDateTime.now().toString()), HttpStatus.OK);
    }

    @GetMapping("/user/{accountNo}")
    public ResponseEntity<Response> getUserAccount(@RequestHeader String token,
                                                 @PathVariable String accountNo){
        return new ResponseEntity<>(new Response("Getting User Account.",bankingAccountService.getUserAccount(token,accountNo),LocalDateTime.now().toString()), HttpStatus.OK);
    }

    @DeleteMapping("/user/{accountNumber}")
    public ResponseEntity<Response> deleteUserAccount(@RequestHeader String token,
                                                      @PathVariable String accountNumber){
        return new ResponseEntity<>(new Response("Deleting User Account.",bankingAccountService.deleteUserAccount(token,accountNumber),LocalDateTime.now().toString()),HttpStatus.OK);
    }

    @GetMapping("/user/accounts")
    public ResponseEntity<Response> getAllUserAccount(){

        return new ResponseEntity<>(new Response("Account Info",bankingAccountService.getAllUsersAccount(),LocalDateTime.now().toString()),HttpStatus.OK);
    }


    /*
    * 1) Add/Remove to address to user account and organisation account.
    * 2)Add/remove kyc details to user account and organisation account.
    * */

    //Organisation

    @PostMapping("/organisation/account")
    public ResponseEntity<Response> createOrganisationAccount(@RequestHeader String token,
                                                            @Valid @RequestBody OrganisationDTO organisationDTO,
                                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Response("Organisation Account",bankingAccountService.addOrganisationAccount(token,organisationDTO),LocalDateTime.now().toString()), HttpStatus.CREATED);
    }

    @PutMapping("/organisation/account")
    public ResponseEntity<Response> editOrganisationAccount(@RequestHeader String token,
                                                          @RequestHeader String accountNumber,
                                                          @Valid @RequestBody OrganisationDTO organisationDTO,
                                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Response(), HttpStatus.OK);
    }

    @GetMapping("/organisation/{accountNumber}")
    public ResponseEntity<Response> getOrganisationAccount(@RequestHeader String token,
                                                         @PathVariable String accountNumber){
        return new ResponseEntity<>(new Response(),HttpStatus.OK);
    }

    @GetMapping("/organisation/accounts")
    public ResponseEntity<Response> getAllOrganisationAccount(){
        return new ResponseEntity<>(new Response(),HttpStatus.OK);
    }




}
