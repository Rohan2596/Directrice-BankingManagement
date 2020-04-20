package com.directrice.banking.service;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.entity.Address;
import com.directrice.banking.respository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankingAddressServiceImpl implements BankingAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public Address addUserAddress(AddressDTO addressDTO) {
        Address address=new Address(addressDTO);
        return userAddressRepository.save(address);
    }
}
