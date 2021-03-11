package com.kloia.atomikos.customerservice.service;

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
                .coreCode(5)
                .coreBalance(BigDecimal.valueOf(5))
                .build();
        return accountCoreRepository.save(accountCore);
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public AccountCore update(AccountCore accountCore) {
        accountCore.setCoreCode(125);
        accountCore.setCoreBalance(BigDecimal.valueOf(125));
        return accountCoreRepository.save(accountCore);
    }


}
