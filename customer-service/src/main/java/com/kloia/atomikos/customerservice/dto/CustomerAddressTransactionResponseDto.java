package com.kloia.atomikos.customerservice.dto;

import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddressTransactionResponseDto {

    private CustomerAddress customerAddress;

    private AccountCore accountCore;

}
