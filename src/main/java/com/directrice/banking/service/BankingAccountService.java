package com.directrice.banking.service;

import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.UserAccount;

public interface BankingAccountService {

    UserAccount addUserAccount(UserAccountDTO userAccountDTO);
}
