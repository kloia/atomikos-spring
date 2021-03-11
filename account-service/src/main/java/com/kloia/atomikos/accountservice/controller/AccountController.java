package com.kloia.atomikos.accountservice.controller;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.service.TransactionalService;
import com.kloia.atomikos.accountservice.service.AccountService;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionalService transactionalService;

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
