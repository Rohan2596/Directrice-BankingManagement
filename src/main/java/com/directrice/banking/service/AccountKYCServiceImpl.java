package com.directrice.banking.service;

import com.directrice.banking.entity.Address;
import com.directrice.banking.entity.KycDetails;
import com.directrice.banking.entity.UserAccount;
import com.directrice.banking.enumeration.KYCStatus;
import com.directrice.banking.exception.BankingException;
import com.directrice.banking.respository.KYCRepository;
import com.directrice.banking.respository.UserAccountRepository;
import com.directrice.banking.supportService.AuthenticationService;
import lombok.Value;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class AccountKYCServiceImpl implements AccountKYCService {

    @Autowired
    private KYCRepository KYCRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;


    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public KycDetails addUserKycDetails(String token, String accountNumber, MultipartFile file) {
        String userId=authenticationService.getUserId(token);
        Optional<UserAccount> userAccountUserId=userAccountRepository.findByUserId(userId);
        if(userAccountUserId.isPresent()){
            Optional<UserAccount> userAccountNO=userAccountRepository.findByAccountNumber(accountNumber);
            if(userAccountNO.isPresent()){
                String url=uploadFile(file);
                KycDetails kycDetails;
                Optional<KycDetails> kycDetailsOptional = KYCRepository.findByUserIdAndAccountNumber(userId,accountNumber);
               if(kycDetailsOptional.isPresent())
               {
                   kycDetails= kycDetailsOptional.get();
                   kycDetails.setKycImageURL(url);
                   System.out.println("goot");
               }else{
                    kycDetails=new KycDetails(url, KYCStatus.PROCESSING_STATE.name(),userId,accountNumber);
                   System.out.println("NEW");
               }
               KYCRepository.save(kycDetails);
               userAccountNO.get().setKycStatus(KYCStatus.PROCESSING_STATE.name());
               userAccountNO.get().setKycDetails(kycDetails);
               userAccountRepository.save(userAccountNO.get());
               return kycDetails;
            }
            throw new BankingException("Invalid Account Number.", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
        }
        throw new BankingException("Invalid User id.", BankingException.ExceptionTypes.INVALID_USER_ID);

    }


//    @Value("${upload.path}")
    private String path="/home/abc/Downloads";

    public String uploadFile(MultipartFile file) {

        try {
            var fileName = file.getOriginalFilename();
            var is = file.getInputStream();

            long filePath=Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return path+fileName;
        } catch (IOException e) {

            var msg = String.format("Failed to store file %f", file.getName());

            throw new BankingException(msg, BankingException.ExceptionTypes.ALREADY_ACCOUNT_CREATED);
        }
    }

    @Override
    public KycDetails getUserKycDetails(String token, String accountNumber) {
        String userId=authenticationService.getUserId(token);
        Optional<KycDetails> kycDetailsOptional = KYCRepository.findByUserIdAndAccountNumber(userId,accountNumber);
        if(kycDetailsOptional.isPresent()){
             return kycDetailsOptional.get();
        }
        throw  new BankingException("", BankingException.ExceptionTypes.INVALID_ACCOUNT_NUMBER);
    }

    @Override
    public List<KycDetails> getAllUserKycDetails() {
        return KYCRepository.findAll();
    }
}
