package com.kloia.atomikos.accountservice.service;

import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.repository.account.AccountRepository;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public Account findById(Integer id) throws NotFoundException {
        Optional<Account> optional = accountRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException("Account with id: " + id + " not found"));
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public Account save() {
        Account account = Account.builder()
                .code(10)
                .balance(BigDecimal.TEN)
                .build();
        return accountRepository.save(account);
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public Account update(Account account) {
        account.setCode(250);
        account.setBalance(BigDecimal.valueOf(250));
        return accountRepository.save(account);
    }

}
