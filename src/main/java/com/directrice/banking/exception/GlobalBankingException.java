package com.directrice.banking.exception;

import com.directrice.banking.controller.BankingController;
import com.directrice.banking.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalBankingException {

    @ExceptionHandler(BankingException.class)
    public ResponseEntity<Response> handleWalletException(BankingException bankingException){
        return new ResponseEntity<>(
                new Response(bankingException.exceptionTypes.errorMessage,bankingException.exceptionTypes, LocalDateTime.now().toString()),
                HttpStatus.BAD_GATEWAY);
    }

}
