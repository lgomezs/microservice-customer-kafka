package com.service.customer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Configuration
public class ConfigTopic {

    //not recommend creat here
    @Bean
    public NewTopic vehicleEvents() {
        return TopicBuilder.name("customer-events")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
