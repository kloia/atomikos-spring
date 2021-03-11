package com.kloia.atomikos.accountservice.service;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.repository.account.AccountRepository;
import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.core.repository.account.AccountCoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCoreTransactionService {

    private final AccountRepository accountRepository;
    private final AccountCoreRepository accountCoreRepository;

    public AccountTransactionResponseDto create(Account customer, AccountCore accountCore) {
        return null;
    }
}
