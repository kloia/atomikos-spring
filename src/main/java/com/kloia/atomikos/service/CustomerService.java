package com.kloia.atomikos.service;

import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.exception.SystemException;
import com.kloia.atomikos.model.customer.Customer;
import com.kloia.atomikos.repository.account.AccountRepository;
import com.kloia.atomikos.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findById(Integer id) throws NotFoundException {
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException("Customer with id: " + id + " not found"));
    }

}
