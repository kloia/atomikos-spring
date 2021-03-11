package com.kloia.atomikos.customerservice.service;

import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findById(Integer id) throws NotFoundException {
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException("Customer with id: " + id + " not found"));
    }

}