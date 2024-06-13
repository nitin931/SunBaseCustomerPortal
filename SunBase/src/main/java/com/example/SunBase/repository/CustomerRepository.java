package com.example.SunBase.repository;

import com.example.SunBase.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Page<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCityContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String firstName, String lastName, String city, String email, String phone, Pageable pageable);

}
