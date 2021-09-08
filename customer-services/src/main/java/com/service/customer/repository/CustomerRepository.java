package com.service.customer.repository;

import com.service.customer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Repository("webCustomerRepository")
public interface CustomerRepository extends MongoRepository<Customer, Long> {


}
