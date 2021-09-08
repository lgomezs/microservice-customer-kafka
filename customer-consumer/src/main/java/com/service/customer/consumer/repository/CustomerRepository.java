package com.service.customer.consumer.repository;

import com.service.customer.consumer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {


}
