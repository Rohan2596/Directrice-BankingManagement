package com.directrice.banking.respository;

import com.directrice.banking.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends MongoRepository<Address,String> {
}
