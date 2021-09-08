package com.service.customer.consumer.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.customer.consumer.service.CustomerService;
import com.service.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Slf4j
@Component
public class CustomerConsumer {
    @Value("${spring.kafka.template.default-topic}")
    private String topic;
    private final CustomerService customerService;

    public CustomerConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = {"customer-events"})
    public void onMessageCustomer(ConsumerRecord<String, CustomerDto> consumerRecord) {
        log.info("####################### ConsumerRecord : {} ", consumerRecord);
        try {
            customerService.processCustomerEvent(consumerRecord);
        } catch (JsonProcessingException e) {
            log.error("Error onMessageCustomer {} {} ", e.getMessage(), e);
        }
    }
}
