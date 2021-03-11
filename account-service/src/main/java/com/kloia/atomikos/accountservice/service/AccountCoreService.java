package com.kloia.atomikos.accountservice.service;

import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.core.repository.account.AccountCoreRepository;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCoreService {

    private final AccountCoreRepository accountCoreRepository;

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public AccountCore findById(Integer id) throws NotFoundException {
        Optional<AccountCore> optional = accountCoreRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException("Account with id: " + id + " not found"));
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public AccountCore save() {
        AccountCore accountCore = AccountCore.builder()
                .coreCode(1)
                .coreBalance(BigDecimal.TEN)
                .build();
        return accountCoreRepository.save(accountCore);
    }

}
