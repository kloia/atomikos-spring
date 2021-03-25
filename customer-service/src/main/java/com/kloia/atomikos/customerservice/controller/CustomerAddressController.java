package com.kloia.atomikos.customerservice.controller;

import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddress;
import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddressId;
import com.kloia.atomikos.customerservice.service.CustomerAddressService;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer-address", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerAddress> createCustomerAddress(@RequestBody CustomerAddressId customerAddressId) {
        CustomerAddress accountAddress = customerAddressService.save(customerAddressId);
        return ResponseEntity.ok(accountAddress);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerAddress>> getAllCustomerAddress() {
        List<CustomerAddress> allCustomerAddress = customerAddressService.getAllCustomer();
        return ResponseEntity.ok(allCustomerAddress);
    }

    @GetMapping(value = "/find-customer-address")
    public ResponseEntity<CustomerAddress> getCustomerAddress(@RequestBody CustomerAddressId customerAddressId) throws NotFoundException {
        CustomerAddress customerAddress = customerAddressService.findByCustomerAddressId(customerAddressId);
        return ResponseEntity.ok(customerAddress);
    }
}
