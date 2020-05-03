package com.directrice.banking.service;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.dto.OrganisationDTO;
import com.directrice.banking.dto.UserAccountDTO;
import com.directrice.banking.entity.*;
import com.directrice.banking.enumeration.AccountStatus;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.OrganisationAccountRepository;
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

    @Mock
    private OrganisationAccountRepository organisationAccountRepository;

    @InjectMocks
    private BankingAccountServiceImpl bankingAccountService;

    private UserAccount userAccount;
    private UserAccountDTO userAccountDTO;
    private OrganisationAccount organisationAccount;
    private OrganisationDTO organisationDTO;
    private Address address;
    private AddressDTO addressDTO;
    private KycDetails kycDetails;
    private String token;
    private UserCardDetails userCardDetails;
    private String accountNo;
    private Balance balance;

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
                "PROCESSING_STATE",
                this.kycDetails,
                this.userCardDetails,
                new Balance()
                );
        this.accountNo="D2020K111081";
        this.organisationDTO=new OrganisationDTO("kadam Groups Pvt Ltd",
                "ABCD1234",
                "www.kadam.com",
                "All types",
                this.addressDTO,
                "78/78/78");
        this.organisationAccount=new OrganisationAccount(
                "5ea01f859ca6df095feef2bd",
                "c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2",
                "kadam Groups PVT",
                "QWER1233",
                "www.kadam.com",
                "CHECKING_ACCOUNT",
                this.addressDTO,
                "78/78/78",
                "D2020K111081123",
                "Legal",
                "Checking_Account",
                this.address,
                this.kycDetails);
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
    //add KYC to user account
    //get all UserAccount
    @Test
    void givenValid_whenVerified_ALL_USER_ACCOUNT_shouldReturnResponse(){

       List<UserAccount> accountList =bankingAccountService.getAllUsersAccount();
       Assertions.assertEquals(0,accountList.size());
    }



    //organisation Account
    //add user Account
    @Test
    void givenValidToken_ValidOrganisationDTO_WhenAdded_shouldReturnListResponse(){
        Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
        Mockito.when(organisationAccountRepository.save(any())).thenReturn(this.organisationAccount);
        OrganisationAccount organisationAccount=bankingAccountService.addOrganisationAccount(this.token,this.organisationDTO);
        Assertions.assertEquals("kadam Groups PVT",organisationAccount.getName());
    }

    //edit user account
    @Test
    void givenValidToken_ValidOrganisationDTO_WhenEdited_shouldReturnListResponse(){
        Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
        Mockito.when(organisationAccountRepository.save(any())).thenReturn(this.organisationAccount);
        Mockito.when(organisationAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.organisationAccount));
        Mockito.when(organisationAccountRepository.findByAccountNo(any())).thenReturn(Optional.of(this.organisationAccount));
        OrganisationAccount organisationAccount=bankingAccountService.editOrganisationAccount(this.token,this.accountNo,this.organisationDTO);
        Assertions.assertEquals("kadam Groups Pvt Ltd",organisationAccount.getName());
    }
    @Test
    void givenInValidToken_ValidOrganisationDTO_WhenEdited_shouldReturnListResponse(){
       try{
        Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
        Mockito.when(organisationAccountRepository.save(any())).thenReturn(this.organisationAccount);
        Mockito.when(organisationAccountRepository.findByAccountNo(any())).thenReturn(Optional.of(this.organisationAccount));
        bankingAccountService.editOrganisationAccount(this.token,this.accountNo,this.organisationDTO);
           }catch (BankingException bankingException){

           Assertions.assertEquals("Invalid User Id.",bankingException.exceptionTypes.errorMessage);
       }
    }

    @Test
    void givenValidTokenInvalidUserID_ValidOrganisationDTO_WhenEdited_shouldReturnListResponse(){
        try{
            Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
            Mockito.when(organisationAccountRepository.save(any())).thenReturn(this.organisationAccount);
        Mockito.when(organisationAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.organisationAccount));
            bankingAccountService.editOrganisationAccount(this.token,this.accountNo,this.organisationDTO);
        }catch (BankingException bankingException){

            Assertions.assertEquals("Invalid Account Number.",bankingException.exceptionTypes.errorMessage);
        }
    }

    //get Organisation
    @Test
    void givenValidTokenAndAccountNumber_Organisation_Account_shouldReturnListResponse(){
        Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
        Mockito.when(organisationAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.organisationAccount));
        Mockito.when(organisationAccountRepository.findByAccountNo(any())).thenReturn(Optional.of(this.organisationAccount));
        OrganisationAccount organisationAccount=bankingAccountService.getOrganisationAccount(this.token,this.accountNo);
        Assertions.assertEquals("kadam Groups PVT",organisationAccount.getName());
    }
    @Test
    void givenInValidTokenAndAccountNumber_Organisation_Account_shouldReturnListResponse(){
       try{
        Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
        Mockito.when(organisationAccountRepository.findByAccountNo(any())).thenReturn(Optional.of(this.organisationAccount));
        bankingAccountService.getOrganisationAccount(this.token,this.accountNo);

    }catch (BankingException bankingException){
           Assertions.assertEquals("Invalid User Id.",bankingException.exceptionTypes.errorMessage);
       }
    }
    @Test
    void givenValidTokenAndInValidAccountNumber_Organisation_Account_shouldReturnListResponse(){
        try{
            Mockito.when(authenticationService.getUserId(this.token)).thenReturn("dsfsdfdsfs");
        Mockito.when(organisationAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.organisationAccount));
            bankingAccountService.getOrganisationAccount(this.token,this.accountNo);

        }catch (BankingException bankingException){
            Assertions.assertEquals("Invalid Account Number.",bankingException.exceptionTypes.errorMessage);
        }
    }


    //get All Organisation Account
    @Test
    void givenValidToken_UserId_ALL_ORganisation_Account_shouldReturnListResponse(){
        List<OrganisationAccount> organisationAccountList=bankingAccountService.getAllOrganisation();
        Assertions.assertEquals(0,organisationAccountList.size());
    }
    //Add  legal user address to Organisation account
    //add KYC to Organisation Account of legal user and organisation


}
