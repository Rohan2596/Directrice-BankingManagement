package com.directrice.banking.respository;

import com.directrice.banking.entity.OrganisationAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganisationAccountRepository extends MongoRepository<OrganisationAccount,String> {
}
