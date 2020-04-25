package com.directrice.banking.service;

import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.OrganisationAccount;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.enumeration.AccountStatus;
import com.directrice.banking.enumeration.AccountType;
import com.directrice.banking.enumeration.KYCStatus;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.UserAccountRepository;
import com.directrice.banking.supportService.AuthenticationService;
import com.directrice.banking.utility.AccountNumberGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankingAccountServiceImpl implements BankingAccountService {

   @Autowired
   private UserAccountRepository userAccountRepository;

   @Autowired
    private AccountNumberGeneration accountNumberGeneration;

   @Autowired
   private AuthenticationService authenticationService;

    private void mapping(UserAccountDTO userAccountDTO,UserAccount userAccount) {
        userAccount.setFirstName(userAccountDTO.getFirstName());
        userAccount.setLastName(userAccountDTO.getLastName());
        userAccount.setBirthDay(userAccountDTO.getBirthDay());
        userAccount.setCountryOfResidence(userAccountDTO.getCountryOfResidence());
        userAccount.setNationality(userAccountDTO.getNationality());
        userAccount.setOccupation(userAccountDTO.getOccupation());
        userAccount.setStatus(AccountStatus.ACTIVE_ACCOUNT.name());
        userAccount.setKycStatus(KYCStatus.PROCESSING_STATE.name());
    }

    @Override
    public UserAccount addUserAccount(String token,UserAccountDTO userAccountDTO) {
       String userId=authenticationService.getUserId(token);
       Optional<UserAccount> userAccountOptional=userAccountRepository.findByUserId(userId);
       if(!userAccountOptional.isPresent()) {
           UserAccount userAccount = new UserAccount();
           userAccount.setUserId(userId);
           mapping(userAccountDTO, userAccount);
           userAccount.setAccountNumber(accountNumberGeneration.accountNumbers(userAccountDTO));
           return userAccountRepository.save(userAccount);
       }
       throw new BankingException("User Already Have an Account.", BankingException.ExceptionTypes.ALREADY_ACCOUNT_CREATED);
    }

    @Override
    public UserAccount editUserAccount(String token, String accountNo, UserAccountDTO userAccountDTO) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountOptional=userAccountRepository.findByUserId(userId);
        if(userAccountOptional.isPresent()){
        Optional<UserAccount> user=userAccountRepository.findByAccountNumber(accountNo);
        if(user.isPresent()) {
            UserAccount userAccount = user.get();
            mapping(userAccountDTO,userAccount);

            return userAccountRepository.save(userAccount);
        }
        throw  new BankingException("InValid Account No.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
    }
        throw  new BankingException("InValid UserId", BankingException.ExceptionTypes.INVALID_USER_ID);

    }

    @Override
    public UserAccount getUserAccount(String token, String accountNo) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccount=  userAccountRepository.findByUserId(userId);
        if(userAccount.isPresent()){
         Optional<UserAccount> userAccountOptional=userAccountRepository.findByAccountNumber(accountNo);
            if(userAccountOptional.isPresent()){
                return userAccountOptional.get();
            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User Id.", BankingException.ExceptionTypes.INVALID_USER_ID);
    }

    @Override
    public Boolean deleteUserAccount(String token, String accountNo) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccount=  userAccountRepository.findByUserId(userId);
        if(userAccount.isPresent()){
            Optional<UserAccount> userAccountOptional=userAccountRepository.findByAccountNumber(accountNo);
            if(userAccountOptional.isPresent()){
                userAccountOptional.get().setStatus(AccountStatus.INACTIVE_ACCOUNT.name());
                userAccountRepository.save(userAccountOptional.get());
                return true;
            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User Id.", BankingException.ExceptionTypes.INVALID_USER_ID);
    }


    @Override
    public List<UserAccount> getAllUsersAccount() {

        return userAccountRepository.findAll();
    }

    @Override
    public OrganisationAccount addOrganisationAccount(OrganisationDTO organisationDTO) {
        return null;
    }

    @Override
    public OrganisationAccount editOrganisationAccount(String token, String organisationAccountId, OrganisationDTO organisationDTO) {
        return null;
    }

    @Override
    public OrganisationAccount getOrganisationAccount(String token, String accountNo) {
        return null;
    }

    @Override
    public Boolean deleteOrganisationAccount(String token, String accountNo) {
        return null;
    }

    @Override
    public List<OrganisationAccount> getAllOrganisation() {
        return null;
    }
}
