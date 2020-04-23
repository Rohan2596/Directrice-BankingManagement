package com.directrice.banking.service;

import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.Address;
import com.directrice.banking.entity.KycDetails;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.entity.UserCardDetails;
import com.directrice.banking.enumeration.AccountStatus;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.UserAccountRepository;
import com.directrice.banking.supportService.AuthenticationService;
import com.directrice.banking.utility.AccountNumberGeneration;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class BankingAccountServiceTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private AccountNumberGeneration accountNumberGeneration;

    @InjectMocks
    private BankingAccountServiceImpl bankingAccountService;

    private UserAccount userAccount;
    private UserAccountDTO userAccountDTO;
    private Address address;
    private KycDetails kycDetails;
    private String token;
    private UserCardDetails userCardDetails;
    private String accountNo;

    @BeforeEach
    void setUp() {
        this.userAccountDTO=new UserAccountDTO("Rohan",
                "kadam",
                "1/01/1990",
                "Indian",
                "India",
                "Services");
        this.userAccount=new UserAccount("5ea01f859ca6df095feef2bd",
                "c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2",
                "ROHAN",
                "KADAM",
                "11/11/11",
                "INDIAN",
                "India",
                "IT Services",
                "NATURAL",
                "D2020K111081",
                Boolean.FALSE,
                AccountStatus.ACTIVE_ACCOUNT.name(),
                "SAVING",
                this.address,
                this.kycDetails,
                this.userCardDetails);
        this.accountNo="D2020K111081";
    }

    //NATURAL USERS
    //add user Account

    @Test
    void givenValidTokenAndUserAccountDTO_whenAdded_shouldReturnResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.empty());
        UserAccount newUserAccount=bankingAccountService.addUserAccount(token,this.userAccountDTO);
        Assertions.assertEquals(newUserAccount.getFirstName(),"ROHAN");
        Assertions.assertEquals(newUserAccount.getAccountNumber(),"D2020K111081");

    }
    @Test
    void givenInValidTokenAndValidUserAccountDTO_whenAdded_shouldReturnResponse(){
       try {
           Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
           Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
         bankingAccountService.addUserAccount(token, this.userAccountDTO);

       }catch (BankingException bankingException){
           Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Account Already Created.");
       }
    }
    //edit user Account
    @Test
    void givenValidTokenAndUserAccountDTOAndValidAccountNo_whenEdited_shouldReturnResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        UserAccount newUserAccount=bankingAccountService.editUserAccount(token,this.accountNo,this.userAccountDTO);
        Assertions.assertEquals(newUserAccount.getFirstName(),"Rohan");
        Assertions.assertEquals(newUserAccount.getAccountNumber(),"D2020K111081");

    }

    @Test
    void givenInValidTokenAndUserAccountDTOAndValidAccountNo_whenEdited_shouldReturnResponse(){
        try{
        Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
//        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        bankingAccountService.editUserAccount(token,this.accountNo,this.userAccountDTO);

    }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid User Id.");

        }
    }

    @Test
    void givenValidTokenAndUserAccountDTOAndInValidAccountNo_whenEdited_shouldReturnResponse(){
        try{
            Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.empty());
            bankingAccountService.editUserAccount(token,this.accountNo,this.userAccountDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid Account Number.");

        }
    }



    //get User Account
    @Test
    void givenValidTokenAndValidAccountNo_whenVerified_GETTING_USER_ACCOUNT_shouldReturnResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        UserAccount newUserAccount=bankingAccountService.getUserAccount(token,this.accountNo);
        Assertions.assertEquals(newUserAccount.getFirstName(),"ROHAN");
        Assertions.assertEquals(newUserAccount.getAccountNumber(),"D2020K111081");

    }

    @Test
    void givenInValidTokenAndValidAccountNo_whenVerified_GETTING_USER_ACCOUNT_shouldReturnResponse(){
        try{
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
       bankingAccountService.getUserAccount(token,this.accountNo);
           }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid User Id.");
        }
    }

    @Test
    void givenValidTokenAndInValidAccountNo_whenVerified_GETTING_USER_ACCOUNT_shouldReturnResponse(){
        try{
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            bankingAccountService.getUserAccount(token,this.accountNo);
        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid Account Number.");

        }
    }

    //delete User Account
    @Test
    void givenValidTokenAndValidAccountNo_whenVerified_DELETE_USER_ACCOUNT_shouldReturnResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        Boolean result=bankingAccountService.deleteUserAccount(token,this.accountNo);
      Assertions.assertTrue(result);
    }

    @Test
    void givenInValidTokenAndValidAccountNo_whenVerified_DELETE_USER_ACCOUNT_shouldReturnResponse(){
        try{
            Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
            bankingAccountService.deleteUserAccount(token,this.accountNo);
        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid User Id.");
        }
    }

    @Test
    void givenValidTokenAndInValidAccountNo_whenVerified_DELETE_USER_ACCOUNT_shouldReturnResponse(){
        try{
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(userAccountRepository.save(any())).thenReturn(this.userAccount);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            bankingAccountService.deleteUserAccount(token,this.accountNo);
        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid Account Number.");

        }
    }
    //Add address to user account
    //add KYC to user account
    //get all UserAccount
    @Test
    void givenValid_whenVerified_ALL_USER_ACCOUNT_shouldReturnResponse(){

       List<UserAccount> accountList =bankingAccountService.getAllUsersAccount();
       Assertions.assertEquals(0,accountList.size());
    }



    //organisation Account
    //add user Account
    //edit user account
    //get Organisation
    //get All Organisation Account
    //Add  legal user address to Organisation account
    //add KYC to Organisation Account of legal user and organisation


}
