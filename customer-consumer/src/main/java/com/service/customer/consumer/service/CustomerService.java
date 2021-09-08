package com.service.customer.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.dto.CustomerDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author Luis Miguel Gomez Saavedra
 */
public interface CustomerService {

    void processCustomerEvent(ConsumerRecord<String, CustomerDto> consumerRecord) throws JsonProcessingException;

    void handleErrorRecovery(ConsumerRecord<String, CustomerDto> consumerRecord);
}
