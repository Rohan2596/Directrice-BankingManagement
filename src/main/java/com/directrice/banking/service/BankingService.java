package com.directrice.banking.service;

import com.directrice.banking.dto.BalanceDTO;
import com.directrice.banking.entity.Balance;

import java.util.List;

public interface BankingService {

    Balance updateBalance(String token,String accountNumber,BalanceDTO balanceDTO);
    Balance getUserbalance(String token,String accountNumber);
    List<Balance> getAllBalances(String token);
}
