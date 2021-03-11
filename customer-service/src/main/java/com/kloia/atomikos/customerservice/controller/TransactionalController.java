package com.kloia.atomikos.customerservice.controller;

import com.kloia.atomikos.customerservice.dto.CustomerAddressAndAccountCoreUpdateRequestDto;
import com.kloia.atomikos.customerservice.dto.CustomerAddressTransactionResponseDto;
import com.kloia.atomikos.customerservice.dto.CustomerTransactionResponseDto;
import com.kloia.atomikos.customerservice.service.TransactionalService;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer-transactions")
public class TransactionalController {

    private final TransactionalService transactionalService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomerAndAccount() {
        CustomerTransactionResponseDto responseDto = transactionalService.createCustomerAndAccountCore();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/{customerId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerTransactionResponseDto> getAccountAndAccountCore(
            @PathVariable("customerId") Integer accountId,
            @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        CustomerTransactionResponseDto customerTransactionResponseDto = transactionalService.getAccountAndAccountCore(accountId, accountCoreId);
        return ResponseEntity.ok(customerTransactionResponseDto);
    }

    @PostMapping(value = "/{customerId}/create-core", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerTransactionResponseDto> createAccountCore(
            @PathVariable("customerId") Integer accountId
    ) throws NotFoundException {
        CustomerTransactionResponseDto account = transactionalService.createAccountCore(accountId);
        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/{customerId}/{accountCoreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerTransactionResponseDto> updateAccountAndAccountCore(
            @PathVariable("customerId") Integer accountId,
            @PathVariable("accountCoreId") Integer accountCoreId
    ) throws NotFoundException {
        CustomerTransactionResponseDto account = transactionalService.updateAccountAndAccountCore(accountId, accountCoreId);
        return ResponseEntity.ok(account);
    }

    @PostMapping(value = "/update/customer-address/account-core", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerAddressTransactionResponseDto> updateAccountAndAccountCore(
            @RequestBody CustomerAddressAndAccountCoreUpdateRequestDto requestDto
    ) throws NotFoundException {
        CustomerAddressTransactionResponseDto account = transactionalService.updateCustomerAddressAndAccountCore(requestDto);
        return ResponseEntity.ok(account);
    }

}
