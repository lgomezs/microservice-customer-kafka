package com.service.customer.service;

import com.service.customer.domain.Customer;

import java.util.List;

/**
 * @author Luis Miguel Gomez Saavedra
 */
public interface CustomerService {

    List<Customer> findAll();

}
