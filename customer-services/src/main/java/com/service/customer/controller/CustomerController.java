package com.service.customer.controller;

import com.service.customer.domain.Customer;
import com.service.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Slf4j
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(@Autowired @Qualifier("webCustomerServiceImpl") CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/api/v1/customer")
    public ResponseEntity<List<Customer>> getCustomerAll() {
        ResponseEntity<List<Customer>> responseEntity = ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
        log.info("/api/v1//customer  {} ", responseEntity);
        return responseEntity;
    }
}
