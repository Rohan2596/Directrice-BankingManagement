package com.directrice.banking.respository;

import com.directrice.banking.entity.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {

    Optional<Balance> findByAccountNo(String accountNumber);
}
