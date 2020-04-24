package com.directrice.banking.service;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.entity.Address;

import java.util.List;

public interface BankingAddressService {

    String addUserAddress(String token,String accountNo,AddressDTO addressDTO);
    String editUserAddress(String token,String accountNo,AddressDTO addressDTO);
    Address getUserAddress(String token,String accountNo);
    List<Address> getAllUserAddress();

}
