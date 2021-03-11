package com.kloia.atomikos.accountservice.controller;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.accountservice.service.AccountCoreTransactionService;
import com.kloia.atomikos.core.model.account.AccountCore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountCoreTransactionService accountCoreTransactionService;

    @PostMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomerAndAccount() {
        Account account = Account.builder()
                .code(11)
                .balance(BigDecimal.ONE)
                .build();

        AccountCore accountCore = AccountCore.builder()
                .coreCode(1)
                .coreBalance(BigDecimal.TEN)
                .build();

        AccountTransactionResponseDto responseDto = accountCoreTransactionService.create(account, accountCore);
        return ResponseEntity.ok(responseDto);
    }

}
