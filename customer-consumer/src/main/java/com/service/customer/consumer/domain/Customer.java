package com.service.customer.consumer.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@Getter
@Setter
@Document(collection = "customer")
public class Customer {
    @Id
    private long id;
    private String numberDocument;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
}
