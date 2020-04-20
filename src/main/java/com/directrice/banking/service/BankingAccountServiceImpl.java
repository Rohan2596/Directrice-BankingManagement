package com.directrice.banking.service;

import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.respository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankingAccountServiceImpl implements BankingAccountService {

   @Autowired
   private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount addUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount=new UserAccount(userAccountDTO);

        return userAccountRepository.save(userAccount);
    }
}
