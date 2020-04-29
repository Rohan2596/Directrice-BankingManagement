package com.directrice.banking.service;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.entity.*;
import com.directrice.banking.enumeration.AccountStatus;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.AddressRepository;
import com.directrice.banking.respository.UserAccountRepository;
import com.directrice.banking.supportService.AuthenticationService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class BankingAddressServiceTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    private AddressDTO addressDTO;
    private Address address;
    private KycDetails kycDetails;
    private String token;
    private UserCardDetails userCardDetails;
    private String userId;
    private String accountNo;
    private UserAccount userAccount;
    private Balance balance;


    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AuthenticationService authenticationService;


    @InjectMocks
    private BankingAddressServiceImpl bankingAddressService;

    @BeforeEach
    void setUp() {
        this.addressDTO=new AddressDTO("Marine Drive","Mumbai","Mumbai","Maharashtra","India","420001");
        this.address=new Address("5ea2a018c030cd45536522d4","c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2","D2020K251049","Marine Drive","Mumbai","Mumbai","Maharashtra","India","420001");
        this.userId="c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2";
        this.accountNo="D2020K251049";
        this.balance=new Balance();
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
                this.balance);
    }

//add Address
    @Test
    void givenValidTokenAndAccountNo_WhenAdded_shouldReturnValidResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(addressRepository.save(any())).thenReturn(this.address);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.empty());
        String addAddress=bankingAddressService.addUserAddress(this.token,this.accountNo,this.addressDTO);
        Assertions.assertEquals(addAddress,"Address Added.");

    }

    @Test
    void givenInvalidTokenAndAccountNo_WhenAdded_shouldReturnValidResponse(){
        try{
            Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.empty());
            bankingAddressService.addUserAddress(this.token,this.accountNo,this.addressDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid User Id.");
        }
    }

    @Test
    void givenValidTokenAndInValidAccountNo_WhenAdded_shouldReturnValidResponse(){
        try{
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(addressRepository.save(any())).thenReturn(this.address);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.empty());
            bankingAddressService.addUserAddress(this.token,this.accountNo,this.addressDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid Account Number.");
        }
    }

    @Test
    void givenValidTokenAndValidAccountNo_ALREADYADDED_WhenAdded_shouldReturnValidResponse(){
        try{
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(addressRepository.save(any())).thenReturn(this.address);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
            bankingAddressService.addUserAddress(this.token,this.accountNo,this.addressDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Account Already Created.");
        }
    }


    //edit Address

    @Test
    void givenValidTokenAndAccountNo_WhenEdited_shouldReturnValidResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(addressRepository.save(any())).thenReturn(this.address);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
        String addAddress=bankingAddressService.editUserAddress(this.token,this.accountNo,this.addressDTO);
        Assertions.assertEquals(addAddress,"Address Edited.");

    }

    @Test
    void givenInvalidTokenAndAccountNo_WhenEdited_shouldReturnValidResponse(){
        try{
            Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
            bankingAddressService.editUserAddress(this.token,this.accountNo,this.addressDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid User Id.");
        }
    }

    @Test
    void givenValidTokenAndInValidAccountNo_WhenEdited_shouldReturnValidResponse(){
        try{
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(addressRepository.save(any())).thenReturn(this.address);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
            bankingAddressService.editUserAddress(this.token,this.accountNo,this.addressDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid Account Number.");
        }
    }

    @Test
    void givenValidTokenAndValidAccountNo_ALREADYADDED_WhenEdited_shouldReturnValidResponse(){
        try{
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(addressRepository.save(any())).thenReturn(this.address);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.empty());
            bankingAddressService.editUserAddress(this.token,this.accountNo,this.addressDTO);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Account Already Created.");
        }
    }
    //Get User address
    @Test
    void givenValidTokenAndAccountNo_WhenGetting_shouldReturnValidResponse(){
        Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
        Mockito.when(addressRepository.save(any())).thenReturn(this.address);
        Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
        Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
        Address newAddress=bankingAddressService.getUserAddress(this.token,this.accountNo);
        Assertions.assertEquals(newAddress.getCountry(),"India");

    }

    @Test
    void givenInValidTokenAndAccountNo_whenGetting_shouldReturnValidResponse(){
      try {
          Mockito.when(userAccountRepository.findByAccountNumber(any())).thenReturn(Optional.of(this.userAccount));
          Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
          Address newAddress = bankingAddressService.getUserAddress(this.token, this.accountNo);
          Assertions.assertEquals(newAddress.getCountry(), "India");
      }catch (BankingException bankingException){
          Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid User Id.");
      }
    }

    @Test
    void givenValidTokenAndAccountNo_whenGetting_shouldReturnValidResponse(){
        try {
            Mockito.when(authenticationService.getUserId(any())).thenReturn("c47dbe5c-5a5b-4195-87e7-ff69cd1e43f2");
            Mockito.when(addressRepository.save(any())).thenReturn(this.address);
            Mockito.when(userAccountRepository.findByUserId(any())).thenReturn(Optional.of(this.userAccount));
            Mockito.when(addressRepository.findByAccountNo(any())).thenReturn(Optional.of(this.address));
            bankingAddressService.getUserAddress(this.token, this.accountNo);

        }catch (BankingException bankingException){
            Assertions.assertEquals(bankingException.exceptionTypes.errorMessage,"Invalid Account Number.");
        }
    }

    //Get all User Address
    @Test
    void givenValidDetails_WhenVerified_ALLADDRESS_shouldReturnValidResponse(){
      List<Address> addressList=bankingAddressService.getAllUserAddress();
      Assertions.assertEquals(addressList.size(),0);
    }
}
