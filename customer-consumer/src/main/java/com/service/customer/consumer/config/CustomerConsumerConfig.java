package com.service.customer.consumer.config;

import com.service.customer.consumer.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Configuration
@Slf4j
@EnableKafka
public class CustomerConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public CustomerConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Customer>
                factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Customer> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(this.kafkaProperties.buildConsumerProperties());
    }

}
