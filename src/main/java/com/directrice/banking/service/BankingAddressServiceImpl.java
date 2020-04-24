package com.directrice.banking.service;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.entity.Address;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.AddressRepository;
import com.directrice.banking.respository.UserAccountRepository;
import com.directrice.banking.supportService.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankingAddressServiceImpl implements BankingAddressService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AddressRepository addressRepository;

    private Address mapping(Address address,AddressDTO addressDTO){
        address.setAddress(addressDTO.getAddress());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setDistrict(addressDTO.getDistrict());
        address.setTown(addressDTO.getTown());
        address.setPostalCode(addressDTO.getPostalCode());
        return address;
    }


   @Override
    public String addUserAddress(String token,String accountNo,AddressDTO addressDTO) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountUserId=userAccountRepository.findByUserId(userId);
        if(userAccountUserId.isPresent()){
            Optional<UserAccount> userAccountNO=userAccountRepository.findByAccountNumber(accountNo);
            if(userAccountNO.isPresent()){
               Optional<Address> optionalAddress=addressRepository.findByAccountNo(accountNo);
               if (optionalAddress.isPresent()){
                   throw  new BankingException("Address Already Added.", BankingException.ExceptionTypes.ALREADY_ACCOUNT_CREATED);
               }

                Address address=new Address();
                mapping(address,addressDTO);
                address.setUserId(userId);
                address.setAccountNo(accountNo);
                addressRepository.save(address);
                userAccountNO.get().setAddress(address);
                userAccountRepository.save(userAccountNO.get());
                return "Address Added.";
            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
       throw new BankingException("Invalid User id.", BankingException.ExceptionTypes.INVALID_USER_ID);

    }

    @Override
    public String editUserAddress(String token, String accountNo, AddressDTO addressDTO) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountUserId=userAccountRepository.findByUserId(userId);
        if(userAccountUserId.isPresent()){
            Optional<UserAccount> userAccountNO=userAccountRepository.findByAccountNumber(accountNo);
            if(userAccountNO.isPresent()){
                Optional<Address> addressOptional=addressRepository.findByAccountNo(accountNo);
                if (!addressOptional.isPresent()){
                    throw  new BankingException("Address Already Added.", BankingException.ExceptionTypes.ALREADY_ACCOUNT_CREATED);
                }
              Address address=addressOptional.get();
                mapping(address,addressDTO);
                addressRepository.save(address);
                userAccountNO.get().setAddress(address);
                userAccountRepository.save(userAccountNO.get());
                return "Address Edited.";
            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User id.", BankingException.ExceptionTypes.INVALID_USER_ID);

    }

    @Override
    public Address getUserAddress(String token, String accountNo) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountUserId=userAccountRepository.findByUserId(userId);
        if(userAccountUserId.isPresent()) {
            Optional<Address> addressOptional = addressRepository.findByAccountNo(accountNo);
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                return address;
            }

            throw new BankingException("Invalid Account no.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User id.", BankingException.ExceptionTypes.INVALID_USER_ID);

    }

    @Override
    public List<Address> getAllUserAddress() {
        return addressRepository.findAll();
    }


}
