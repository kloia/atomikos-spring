package com.kloia.atomikos.accountservice.service;

import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.repository.account.AccountRepository;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account findById(Integer id) throws NotFoundException {
        Optional<Account> optional = accountRepository.findById(id);
        return optional.orElseThrow(() -> new NotFoundException("Account with id: " + id + " not found"));
    }
}
