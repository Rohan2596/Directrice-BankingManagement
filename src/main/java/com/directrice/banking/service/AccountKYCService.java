package com.directrice.banking.service;

import com.directrice.banking.entity.KycDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountKYCService {

    KycDetails addUserKycDetails(String token, String accountNumber, MultipartFile file);
    KycDetails editUserKycDetails(String token, String accountNumber, MultipartFile file);
    KycDetails getUserKycDetails(String token,String accountNo);
    List<KycDetails> getAllUserKycDetails();
}
