package com.kloia.atomikos.controller;

import com.kloia.atomikos.dto.CustomerAccountCreateResponseDto;
import com.kloia.atomikos.model.account.Account;
import com.kloia.atomikos.model.customer.Customer;
import com.kloia.atomikos.service.CustomerAccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final CustomerAccountTransactionService customerAccountTransactionService;

    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomerAndAccount() {
        Customer customer = Customer.builder()
                .name("test")
                .age(30)
                .build();

        Account account = Account.builder()
                .code(1)
                .balance(BigDecimal.TEN)
                .build();

        CustomerAccountCreateResponseDto responseDto = customerAccountTransactionService.create(customer, account);
        return ResponseEntity.ok(responseDto);
    }

}
