package com.kloia.atomikos.customerservice.controller;

import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddress;
import com.kloia.atomikos.customerservice.service.CustomerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer-address", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerAddress> createCustomer() {
        CustomerAddress accountAddress = customerAddressService.save();
        return ResponseEntity.ok(accountAddress);
    }
}
