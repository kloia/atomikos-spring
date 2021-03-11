package com.kloia.atomikos.accountservice.controller;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.service.TransactionalService;
import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/account-transactions")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final TransactionalService transactionalService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomerAndAccount() {
        Account account = Account.builder()
                .code(11)
                .balance(BigDecimal.ONE)
                .build();

        AccountCore accountCore = AccountCore.builder()
                .coreCode(1)
                .coreBalance(BigDecimal.TEN)
                .build();

        AccountTransactionResponseDto responseDto = transactionalService.create(account, accountCore);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/{accountId}/create-core", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransactionResponseDto> getAccountAndSaveAccountCore(@PathVariable("accountId") Integer accountId
    ) throws NotFoundException {
        AccountTransactionResponseDto account = transactionalService.saveAccountAndAccountCore(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping(value = "/{accountId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransactionResponseDto> getAccount(@PathVariable("accountId") Integer accountId,
                                                                    @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        AccountTransactionResponseDto account = transactionalService.getAccount(accountId, accountCoreId);
        return ResponseEntity.ok(account);
    }
}
