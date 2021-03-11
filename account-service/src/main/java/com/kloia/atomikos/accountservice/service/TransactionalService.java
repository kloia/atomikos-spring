package com.kloia.atomikos.accountservice.service;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.repository.account.AccountRepository;
import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.core.repository.account.AccountCoreRepository;
import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.exception.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final AccountRepository accountRepository;
    private final AccountCoreRepository accountCoreRepository;
    private final AccountService accountService;
    private final AccountCoreService accountCoreService;

    @Transactional(transactionManager = "transactionManager")
    public AccountTransactionResponseDto create(Account account, AccountCore accountCore) {
        Account savedAccount = accountRepository.save(account);
        AccountCore savedAccountCore = accountCoreRepository.save(accountCore);
        return AccountTransactionResponseDto.builder().account(savedAccount).accountCore(savedAccountCore).build();
    }

    @Transactional(transactionManager = "transactionManager", rollbackFor = SystemException.class)
    public void createWithException(Account account, AccountCore accountCore) throws SystemException {
        accountRepository.save(account);
        accountCoreRepository.save(accountCore);
        throw new SystemException();
    }

    @Transactional(transactionManager = "transactionManager")
    public AccountTransactionResponseDto getAccount(Integer accountId, Integer accountCoreId) throws NotFoundException {
        Account account = accountService.findById(accountId);
        AccountCore accountCore = accountCoreService.findById(accountCoreId);
        return AccountTransactionResponseDto.builder().account(account).accountCore(accountCore).build();
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public AccountTransactionResponseDto saveAccountAndAccountCore(Integer accountId) throws NotFoundException {
        Account account = accountService.findById(accountId);
        AccountCore savedAccountCore = accountCoreService.save();
        return AccountTransactionResponseDto.builder().account(account).accountCore(savedAccountCore).build();
    }

}
