package com.example.SunBase.service;

import com.example.SunBase.models.Customer;
import com.example.SunBase.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Page<Customer> getAllCustomers(int page, int size, String sortBy, String direction, String search) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return customerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCityContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                    search, search, search, search, search, pageable);
        }
        return customerRepository.findAll(pageable);
    }

    public void deleteCustomer(UUID uuid) {
        customerRepository.deleteById(uuid);
    }

    public Optional<Customer> getCustomerById(UUID uuid) {
        return customerRepository.findById(uuid);
    }

    public void saveAllCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }
}
