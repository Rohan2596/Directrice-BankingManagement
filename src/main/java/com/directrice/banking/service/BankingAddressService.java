package com.directrice.banking.service;

import com.directrice.banking.dto.AddressDTO;
import com.directrice.banking.entity.Address;

public interface BankingAddressService {

    Address addUserAddress(AddressDTO addressDTO);
}
