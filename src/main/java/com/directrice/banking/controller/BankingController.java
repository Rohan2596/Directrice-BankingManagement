package com.directrice.banking.controller;

import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/directrice/banking")
public class BankingController {


    @PostMapping("/user")
    public ResponseEntity<String> createUserAccount(@RequestHeader String token,
                                                @Valid @RequestBody  UserAccountDTO userAccountDTO,
                                                BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            new ResponseEntity<>(bindingResult.getAllErrors().get(0),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUserAccount(@RequestHeader String token,
                                                    @Valid @RequestBody UserAccountDTO userAccountDTO,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            new ResponseEntity<>(bindingResult.getAllErrors().get(0),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/user/{accountId}")
    public ResponseEntity<String> getUserAccount(@RequestHeader String token,
                                                 @PathVariable String accountId){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<String> getAllUserAccount(){
            return new ResponseEntity<>("",HttpStatus.OK);
    }

    //Organisation

    @PostMapping("/organisation")
    public ResponseEntity<String> createOrganisationAccount(@RequestHeader String token,
                                                    @Valid @RequestBody OrganisationDTO organisationDTO,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            new ResponseEntity<>(bindingResult.getAllErrors().get(0),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PutMapping("/organisation")
    public ResponseEntity<String> editOrganisationAccount(@RequestHeader String token,
                                                            @Valid @RequestBody OrganisationDTO organisationDTO,
                                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            new ResponseEntity<>(bindingResult.getAllErrors().get(0),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/organisation/{accountId}")
    public ResponseEntity<String> getOrganisationAccount(@RequestHeader String token,
                                                         @PathVariable String accountId){
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @GetMapping("/organisations")
    public ResponseEntity<String> getAllOrganisationAccount(){
        return new ResponseEntity<>("",HttpStatus.OK);
    }




}
