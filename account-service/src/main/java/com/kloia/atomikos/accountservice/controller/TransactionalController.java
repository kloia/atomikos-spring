package com.kloia.atomikos.accountservice.controller;

import com.kloia.atomikos.accountservice.dto.AccountTransactionResponseDto;
import com.kloia.atomikos.accountservice.service.TransactionalService;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account-transactions")
@RequiredArgsConstructor
public class TransactionalController {

    private final TransactionalService transactionalService;

    @GetMapping(value = "/{accountId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransactionResponseDto> getAccountAndAccountCore(
            @PathVariable("accountId") Integer accountId,
            @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        AccountTransactionResponseDto account = transactionalService.getAccountAndAccountCore(accountId, accountCoreId);
        return ResponseEntity.ok(account);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAccountAndAccountCore() {
        AccountTransactionResponseDto responseDto = transactionalService.createAccountAndAccountCore();
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "/{accountId}/create-core", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransactionResponseDto> createAccountCore(
            @PathVariable("accountId") Integer accountId
    ) throws NotFoundException {
        AccountTransactionResponseDto account = transactionalService.createAccountCore(accountId);
        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/{accountId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransactionResponseDto> updateAccountAndAccountCore(
            @PathVariable("accountId") Integer accountId,
            @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        AccountTransactionResponseDto account = transactionalService.updateAccountAndAccountCore(accountId, accountCoreId);
        return ResponseEntity.ok(account);
    }

}
