package com.directrice.banking.respository;

import com.directrice.banking.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<Address,String> {

}
