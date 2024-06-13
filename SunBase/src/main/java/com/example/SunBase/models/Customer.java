package com.example.SunBase.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonProperty("first_name")
    private String firstName;  // Use camelCase in Java, but map to snake_case in JSON
    @JsonProperty("last_name")
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;

    public void updateWith(Customer customer) {
        this.firstName = customer.firstName;
        this.lastName = customer.lastName;
        this.street = customer.street;
        this.address = customer.address;
        this.city = customer.city;
        this.state = customer.state;
        this.email = customer.email;
        this.phone = customer.phone;
    }
}