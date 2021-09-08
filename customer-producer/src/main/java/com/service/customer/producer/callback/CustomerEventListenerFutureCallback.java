package com.service.customer.producer.callback;


import com.service.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class CustomerEventListenerFutureCallback implements ListenableFutureCallback<SendResult<String, CustomerDto>> {

    private final String key;
    private final CustomerDto value;

    public CustomerEventListenerFutureCallback(String key, CustomerDto value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void onFailure(Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }

    @Override
    public void onSuccess(SendResult<String, CustomerDto> result) {
        assert result != null;
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
}
