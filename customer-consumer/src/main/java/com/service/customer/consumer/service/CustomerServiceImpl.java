package com.service.customer.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.customer.consumer.callback.CustomerEventListenableFutureCallback;
import com.service.customer.consumer.domain.Customer;
import com.service.customer.consumer.repository.CustomerRepository;
import com.service.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;
    private final ObjectMapper objectMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, KafkaTemplate<String, CustomerDto> kafkaTemplate, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void processCustomerEvent(ConsumerRecord<String, CustomerDto> consumerRecord) throws JsonProcessingException {
        CustomerDto customerDto = consumerRecord.value();
        Customer customer = objectMapper.readValue(objectMapper.writeValueAsString(customerDto), Customer.class);
        log.info("customer to save : {} ", objectMapper.writeValueAsString(customer));
        customerRepository.save(customer);
    }

    @Override
    public void handleErrorRecovery(ConsumerRecord<String, CustomerDto> consumerRecord) {
        log.error("handleRecovery for {}", consumerRecord);
        String key = consumerRecord.key();
        CustomerDto customerDto = consumerRecord.value();
        ListenableFuture<SendResult<String, CustomerDto>> listenableFuture = kafkaTemplate.sendDefault(key, customerDto);
        listenableFuture.addCallback(new CustomerEventListenableFutureCallback(key, customerDto));
    }
}
