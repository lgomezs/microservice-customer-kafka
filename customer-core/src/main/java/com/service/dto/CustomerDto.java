package com.service.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Luis Miguel Gomez Saavedra
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto implements Serializable {
    private long id;
    private String numberDocument;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
}
