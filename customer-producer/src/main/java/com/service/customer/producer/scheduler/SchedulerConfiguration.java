package com.service.customer.producer.scheduler;

import com.github.javafaker.Faker;
import com.service.customer.producer.service.CustomerService;
import com.service.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfiguration {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private final CustomerService customerService;

    public SchedulerConfiguration(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Scheduled(fixedRate = 6000)
    public void scheduleByFixedRate() throws Exception {
        log.info("Scheduler is executing " + format.format(Calendar.getInstance().getTime()) + "\n");
        Faker faker = new Faker();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(faker.number().numberBetween(1, 500));
        customerDto.setNumberDocument(String.valueOf(faker.number().randomNumber(8, true)));
        customerDto.setFirstName(faker.name().firstName());
        customerDto.setLastName(faker.name().lastName());
        customerDto.setUsername(faker.name().username());
        customerDto.setAddress(faker.address().fullAddress());
        customerService.sendCustomerEvents(customerDto);
    }

}
