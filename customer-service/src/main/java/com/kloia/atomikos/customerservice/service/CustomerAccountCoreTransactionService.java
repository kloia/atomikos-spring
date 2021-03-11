package com.kloia.atomikos.customerservice.service;

import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.core.repository.account.AccountCoreRepository;
import com.kloia.atomikos.customerservice.dto.CustomerAccountCreateResponseDto;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.repository.customer.CustomerRepository;
import com.kloia.atomikos.exception.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomerAccountCoreTransactionService {

    private final CustomerRepository customerRepository;
    private final AccountCoreRepository accountCoreRepository;

    @Transactional(transactionManager = "transactionManager")
    public CustomerAccountCreateResponseDto create(Customer customer, AccountCore accountCore) {
        Customer savedCustomer = customerRepository.save(customer);
        AccountCore savedAccountCore = accountCoreRepository.save(accountCore);
        return CustomerAccountCreateResponseDto.builder().customer(savedCustomer).accountCore(savedAccountCore).build();
    }

    @Transactional(transactionManager = "transactionManager", rollbackFor = SystemException.class)
    public void createWithException(Customer customer, AccountCore accountCore) throws SystemException {
        customerRepository.save(customer);
        accountCoreRepository.save(accountCore);
        throw new SystemException();
    }

}
