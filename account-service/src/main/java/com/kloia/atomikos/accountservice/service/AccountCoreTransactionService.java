package com.kloia.atomikos.accountservice.service;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.repository.account.AccountRepository;
import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.core.repository.account.AccountCoreRepository;
import com.kloia.atomikos.exception.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountCoreTransactionService {

    private final AccountRepository accountRepository;
    private final AccountCoreRepository accountCoreRepository;

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

}
