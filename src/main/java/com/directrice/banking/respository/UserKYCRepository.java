package com.directrice.banking.respository;

import com.directrice.banking.entity.KycDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserKYCRepository extends MongoRepository<KycDetails,String> {
}
