package com.kloia.atomikos.customerservice.service;

import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddress;
import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddressId;
import com.kloia.atomikos.customerservice.repository.customeraddress.CustomerAddressRepository;
import com.kloia.atomikos.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddress findByCustomerAddressId(CustomerAddressId customerAddressId) throws NotFoundException {
        Optional<CustomerAddress> optional = customerAddressRepository.findById(customerAddressId);
        return optional.orElseThrow(() -> new NotFoundException("Customer with id: " + customerAddressId + " not found"));
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public CustomerAddress save() {
        CustomerAddress customerAddress = CustomerAddress.builder()
                .id(CustomerAddressId.builder().zipCode("123").id(1).build())
                .neighborhood("Merkez")
                .street("100.Sokak")
                .build();
        return customerAddressRepository.save(customerAddress);
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
    public CustomerAddress update(CustomerAddress customerAddress) {
        customerAddress.setNeighborhood("Cumhuriyet");
        return customerAddressRepository.save(customerAddress);
    }
}
