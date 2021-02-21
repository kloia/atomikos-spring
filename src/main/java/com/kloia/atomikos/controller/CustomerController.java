package com.kloia.atomikos.controller;

import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.model.customer.Customer;
import com.kloia.atomikos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> all = customerService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) throws NotFoundException {
        Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(customer);
    }

}
