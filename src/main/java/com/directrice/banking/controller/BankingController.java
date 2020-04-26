package com.directrice.banking.controller;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.dto.BalanceDTO;
import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.Balance;
import com.directrice.banking.response.Response;
import com.directrice.banking.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/directrice/banking/balance")
public class BankingController {

/*
* 1)To credit and debit of balance account
* 2)to transfer
* 3)loaning porcess.
* 4)emi process to for other vendors
* */

@Autowired
private BankingServiceImpl bankingService;

  @PostMapping
  public ResponseEntity<Response>updateBalance(@RequestHeader String token,
                                               @RequestParam String accountNumber,
                                               @RequestBody  BalanceDTO balanceDTO){
      return new ResponseEntity<>(new Response("",bankingService.updateBalance(token, accountNumber, balanceDTO),LocalDateTime.now().toString()),HttpStatus.OK);
  }

}
