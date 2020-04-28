package com.directrice.banking.service;

import com.directrice.banking.dto.BalanceDTO;
import com.directrice.banking.entity.Address;
import com.directrice.banking.entity.Balance;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.BalanceRepository;
import com.directrice.banking.respository.UserAccountRepository;
import com.directrice.banking.supportService.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankingServiceImpl implements BankingService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BalanceRepository balanceRepository;


    @Override
    public Balance addBalance(String token, String accountNumber, BalanceDTO balanceDTO) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountUserId=userAccountRepository.findByUserId(userId);
        if(userAccountUserId.isPresent()){
            Optional<UserAccount> userAccountNO=userAccountRepository.findByAccountNumber(accountNumber);
            if(userAccountNO.isPresent()){

                Balance balance=new Balance();
                balance.setAccountNo(accountNumber);
                balance.setUserId(userId);
                balance.setAmount(balanceDTO.getAmount());
                balance.setCurrency(balanceDTO.getCurrency());
                Balance addBalance=balanceRepository.save(balance);
                userAccountNO.get().setBalance(addBalance);
                userAccountRepository.save(userAccountNO.get());

                return balance;

            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User id.", BankingException.ExceptionTypes.INVALID_USER_ID);



    }

    @Override
    public String updateBalance(String token, String accountNumber, BalanceDTO balanceDTO) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountUserId=userAccountRepository.findByUserId(userId);
        if(userAccountUserId.isPresent()){
            Optional<UserAccount> userAccountNO=userAccountRepository.findByAccountNumber(accountNumber);
            if(userAccountNO.isPresent()){

               Balance balance=balanceRepository.findByAccountNo(accountNumber)
                        .orElseThrow(() -> new BankingException("", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER));
               balance.setAmount(balanceDTO.getAmount());
               Balance addBalance=balanceRepository.save(balance);
               userAccountNO.get().setBalance(addBalance);
               userAccountRepository.save(userAccountNO.get());
               return String.valueOf(balance.getAmount());

            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User id.", BankingException.ExceptionTypes.INVALID_USER_ID);

    }

    @Override
    public Balance getUserbalance(String token, String accountNumber) {
        Balance balance=balanceRepository.findByAccountNo(accountNumber)
                        .orElseThrow(() -> new BankingException("", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER));


        return balance;
    }

    @Override
    public List<Balance> getAllBalances(String token) {
        return balanceRepository.findAll();
    }
}
