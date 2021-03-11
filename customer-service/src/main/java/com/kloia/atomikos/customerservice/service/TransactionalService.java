package com.kloia.atomikos.customerservice.service;

import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.core.repository.account.AccountCoreRepository;
import com.kloia.atomikos.customerservice.dto.CustomerAccountCreateResponseDto;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.repository.customer.CustomerRepository;
import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.exception.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final CustomerRepository customerRepository;
    private final AccountCoreRepository accountCoreRepository;
    private final AccountCoreService accountCoreService;
    private final CustomerService customerService;

    @Transactional(transactionManager = "transactionManager")
    public CustomerAccountCreateResponseDto createCustomerAndAccountCore() {
        Customer savedCustomer = customerService.save();
        AccountCore savedAccountCore = accountCoreService.save();
        return CustomerAccountCreateResponseDto.builder().customer(savedCustomer).accountCore(savedAccountCore).build();
    }

    @Transactional(transactionManager = "transactionManager")
    public CustomerAccountCreateResponseDto getAccountAndAccountCore(Integer accountId, Integer accountCoreId) throws NotFoundException {
        Customer customer = customerService.findById(accountId);
        AccountCore accountCore = accountCoreService.findById(accountCoreId);
        return CustomerAccountCreateResponseDto.builder().customer(customer).accountCore(accountCore).build();
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public CustomerAccountCreateResponseDto createAccountCore(Integer accountId) throws NotFoundException {
        Customer customer = customerService.findById(accountId);
        AccountCore savedAccountCore = accountCoreService.save();
        return CustomerAccountCreateResponseDto.builder().customer(customer).accountCore(savedAccountCore).build();
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public CustomerAccountCreateResponseDto updateAccountAndAccountCore(Integer accountId, Integer accountCoreId) throws NotFoundException {
        Customer customer = customerService.findById(accountId);
        AccountCore accountCore = accountCoreService.findById(accountCoreId);

        Customer updatedCustomer = customerService.update(customer);
        AccountCore updatedAccountCore = accountCoreService.update(accountCore);
        return CustomerAccountCreateResponseDto.builder().customer(updatedCustomer).accountCore(updatedAccountCore).build();
    }

    @Transactional(transactionManager = "transactionManager", rollbackFor = SystemException.class)
    public void createWithException(Customer customer, AccountCore accountCore) throws SystemException {
        customerRepository.save(customer);
        accountCoreRepository.save(accountCore);
        throw new SystemException();
    }

}
