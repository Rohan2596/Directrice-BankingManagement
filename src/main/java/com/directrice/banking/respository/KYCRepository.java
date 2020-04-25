package com.directrice.banking.respository;

import com.directrice.banking.entity.KycDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KYCRepository extends MongoRepository<KycDetails,String> {

    Optional<KycDetails> findByUserIdAndAccountNumber(String userId,String accountNumber);
}
