package com.service.customer.producer.service;

import com.service.customer.producer.callback.CustomerEventListenerFutureCallback;
import com.service.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@Component
@Slf4j
public class CustomerService {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;
    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;

    public CustomerService(KafkaTemplate<String, CustomerDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, CustomerDto>> sendCustomerEvents(CustomerDto customerDto) {
        String key = customerDto.getUsername();
        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "Rest Call".getBytes()));
        ProducerRecord<String, CustomerDto> producerRecord = new ProducerRecord<>(topic, null, key, customerDto, recordHeaders);

        ListenableFuture<SendResult<String, CustomerDto>> listenableFuture = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new CustomerEventListenerFutureCallback(key, customerDto));
        return listenableFuture;
    }

}
