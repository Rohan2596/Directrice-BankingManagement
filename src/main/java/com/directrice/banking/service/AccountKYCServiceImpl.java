package com.directrice.banking.service;

import com.directrice.banking.entity.KycDetails;
import com.directrice.banking.respository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AccountKYCServiceImpl implements AccountKYCService {

    @Autowired
    private KYCRepository KYCRepository;


    @Override
    public KycDetails addUserKycDetails(String token, String accountNumber, MultipartFile file) {
        return null;
    }

    @Override
    public KycDetails editUserKycDetails(String token, String accountNumber, MultipartFile file) {
        return null;
    }

    @Override
    public KycDetails getUserKycDetails(String token, String accountNo) {
        return null;
    }

    @Override
    public List<KycDetails> getAllUserKycDetails() {
        return null;
    }
}
