package com.kloia.atomikos.customerservice.controller;

import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.customerservice.dto.CustomerAccountCreateResponseDto;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.service.CustomerAccountCoreTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CustomerTransactionController {

    private final CustomerAccountCoreTransactionService customerAccountCoreTransactionService;

    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCustomerAndAccount() {
        Customer customer = Customer.builder()
                .name("test")
                .age(30)
                .build();

        AccountCore accountCore = AccountCore.builder()
                .coreCode(1)
                .coreBalance(BigDecimal.TEN)
                .build();

        CustomerAccountCreateResponseDto responseDto = customerAccountCoreTransactionService.create(customer, accountCore);
        return ResponseEntity.ok(responseDto);
    }

}
