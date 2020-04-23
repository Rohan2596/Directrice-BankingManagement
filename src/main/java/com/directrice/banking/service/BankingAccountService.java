package com.directrice.banking.service;

import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.OrganisationAccount;
import com.directrice.banking.entity.UserAccount;

import java.util.List;

public interface BankingAccountService {

    UserAccount addUserAccount(String token,UserAccountDTO userAccountDTO);
    UserAccount editUserAccount(String token,String userAccountId, UserAccountDTO userAccountDTO);
    UserAccount getUserAccount(String token,String accountNo);
    Boolean deleteUserAccount(String token,String accountNo);
    List<UserAccount> getAllUsersAccount();

    ////////////////////////////////////////

    OrganisationAccount addOrganisationAccount(OrganisationDTO organisationDTO);
    OrganisationAccount editOrganisationAccount(String token,String organisationAccountId,OrganisationDTO organisationDTO);
    OrganisationAccount getOrganisationAccount(String token,String accountNo);
    Boolean deleteOrganisationAccount(String token,String accountNo);
    List<OrganisationAccount> getAllOrganisation();

}
