package com.kloia.atomikos.service;

import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.exception.SystemException;
import com.kloia.atomikos.model.account.Account;
import com.kloia.atomikos.repository.account.AccountRepository;
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
