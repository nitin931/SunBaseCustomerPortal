package com.example.SunBase.controller;

import com.example.SunBase.models.Customer;
import com.example.SunBase.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        System.out.print(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
    }

    @GetMapping("/allCustomers")
    public Page<Customer> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String search) {
        return customerService.getAllCustomers(page, size, sortBy, direction, search);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID uuid) {
        customerService.deleteCustomer(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getCustomerById/{uuid}")
    public Optional<Customer> getCustomerById(@PathVariable UUID uuid) {
        return customerService.getCustomerById(uuid);

    }


    @PutMapping("/update/{uuid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID uuid, @RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customerService.getCustomerById(uuid);
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.updateWith(customer);
            return ResponseEntity.ok(customerService.saveCustomer(updatedCustomer));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/syncCustomers")
    public ResponseEntity<Void> syncCustomers(@RequestBody List<Customer> customers) {

        customerService.saveAllCustomers(customers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
