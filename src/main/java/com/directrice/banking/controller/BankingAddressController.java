package com.directrice.banking.controller;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.response.Response;
import com.directrice.banking.service.BankingAddressService;
import com.directrice.banking.service.BankingAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/directrice/banking")
public class BankingAddressController {

    @Autowired
    private BankingAddressServiceImpl bankingAddressService;

    @PostMapping("/address")
    public ResponseEntity<Response> addAddressToAccount(@RequestHeader String token,
                                                        @RequestHeader String accountNumber,
                                                        @Valid @RequestBody AddressDTO addressDTO,
                                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",bindingResult.getAllErrors().get(0).getDefaultMessage(),LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        String addAddress=bankingAddressService.addUserAddress(token,accountNumber,addressDTO);
        return new ResponseEntity<Response>(new Response("Address Created.",addAddress,LocalDateTime.now().toString()), HttpStatus.CREATED);
    }

    @PutMapping("/address")
    public ResponseEntity<Response> editAddressToAccount(@RequestHeader String token,
                                                         @RequestHeader String accountNumber,
                                                         @Valid  @RequestBody AddressDTO addressDTO,
                                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response("Error Message.",
                                                            bindingResult.getAllErrors().get(0).getDefaultMessage(),
                                                            LocalDateTime.now().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        String updateAddress=bankingAddressService.editUserAddress(token,accountNumber,addressDTO);
        return new ResponseEntity<Response>(new Response("Updated Account Address.",updateAddress,LocalDateTime.now().toString()), HttpStatus.CREATED);
    }

    @GetMapping("/address")
    public ResponseEntity<Response> getUserAddress(@RequestHeader String token,
                                                   @RequestHeader String accountNumber){

        return new ResponseEntity<Response>(new Response("User Address For no:-"+accountNumber,bankingAddressService.getUserAddress(token, accountNumber),LocalDateTime.now().toString()), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllUserAddress(){
        return new ResponseEntity<Response>(new Response( "All User Address",bankingAddressService.getAllUserAddress(),LocalDateTime.now().toString()), HttpStatus.OK);

    }
}
