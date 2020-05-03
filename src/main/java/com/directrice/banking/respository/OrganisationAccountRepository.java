package com.directrice.banking.respository;

import com.directrice.banking.entity.OrganisationAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganisationAccountRepository extends MongoRepository<OrganisationAccount,String> {

    Optional<OrganisationAccount> findByUserId(String userId);
    Optional<OrganisationAccount> findByAccountNo(String accountNumber);
}
