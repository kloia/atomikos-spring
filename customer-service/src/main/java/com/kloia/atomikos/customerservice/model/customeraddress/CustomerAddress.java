package com.kloia.atomikos.customerservice.model.customeraddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CUSTOMER_ADDRESS", schema = "C##CUSTOMER")
public class CustomerAddress {

    @EmbeddedId
    private CustomerAddressId id;

    @NotNull
    private String neighborhood;

    @NotNull
    private String street;

}
