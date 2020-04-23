package com.directrice.banking.service;

import com.directrice.banking.entity.KycDetails;

import java.util.List;

public interface AccountKYCService {

    KycDetails addUserKycDetails();
    KycDetails editUserKycDetails(String token,String accountNo);
    KycDetails getUserKycDetails(String token,String accountNo);
    List<KycDetails> getAllUserKycDetails();
}
