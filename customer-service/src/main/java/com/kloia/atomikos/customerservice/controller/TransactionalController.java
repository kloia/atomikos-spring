package com.kloia.atomikos.customerservice.controller;

import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.customerservice.dto.CustomerAccountCreateResponseDto;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.service.TransactionalService;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer-transactions")
public class TransactionalController {

    private final TransactionalService transactionalService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomerAndAccount() {
        CustomerAccountCreateResponseDto responseDto = transactionalService.createCustomerAndAccountCore();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/{customerId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerAccountCreateResponseDto> getAccountAndAccountCore(
            @PathVariable("customerId") Integer accountId,
            @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        CustomerAccountCreateResponseDto customerAccountCreateResponseDto = transactionalService.getAccountAndAccountCore(accountId, accountCoreId);
        return ResponseEntity.ok(customerAccountCreateResponseDto);
    }

    @PostMapping(value = "/{customerId}/create-core", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerAccountCreateResponseDto> createAccountCore(
            @PathVariable("customerId") Integer accountId
    ) throws NotFoundException {
        CustomerAccountCreateResponseDto account = transactionalService.createAccountCore(accountId);
        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/{customerId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerAccountCreateResponseDto> updateAccountAndAccountCore(
            @PathVariable("customerId") Integer accountId,
            @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        CustomerAccountCreateResponseDto account = transactionalService.updateAccountAndAccountCore(accountId, accountCoreId);
        return ResponseEntity.ok(account);
    }
}
