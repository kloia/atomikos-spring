package com.kloia.atomikos.service;

import com.kloia.atomikos.dto.CustomerAccountCreateResponseDto;
import com.kloia.atomikos.exception.SystemException;
import com.kloia.atomikos.model.account.Account;
import com.kloia.atomikos.model.customer.Customer;
import com.kloia.atomikos.repository.account.AccountRepository;
import com.kloia.atomikos.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomerAccountTransactionService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Transactional(transactionManager = "transactionManager")
    public CustomerAccountCreateResponseDto create(Customer customer, Account account) {
        Customer savedCustomer = customerRepository.save(customer);
        Account savedAccount = accountRepository.save(account);
        return CustomerAccountCreateResponseDto.builder().customer(savedCustomer).account(savedAccount).build();
    }

    @Transactional(transactionManager = "transactionManager", rollbackFor = SystemException.class)
    public void createWithException(Customer customer, Account account) throws SystemException {
        customerRepository.save(customer);
        accountRepository.save(account);
        throw new SystemException();
    }

}
