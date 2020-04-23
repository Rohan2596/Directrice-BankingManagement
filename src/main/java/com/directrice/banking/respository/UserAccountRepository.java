package com.directrice.banking.respository;

import com.directrice.banking.entity.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount,String> {

    Optional<UserAccount> findByAccountNumber(String accountNo);
    Optional<UserAccount> findByUserId(String userId);


}
