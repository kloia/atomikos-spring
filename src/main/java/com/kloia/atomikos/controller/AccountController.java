package com.kloia.atomikos.controller;

import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.exception.SystemException;
import com.kloia.atomikos.model.account.Account;
import com.kloia.atomikos.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> all = accountService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountById(@PathVariable Integer accountId) throws NotFoundException {
        Account account = accountService.findById(accountId);
        return ResponseEntity.ok(account);
    }

}
