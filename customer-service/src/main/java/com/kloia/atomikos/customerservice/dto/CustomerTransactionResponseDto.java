package com.kloia.atomikos.customerservice.dto;

import com.kloia.atomikos.core.model.account.AccountCore;
import com.kloia.atomikos.customerservice.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerTransactionResponseDto {

    private Customer customer;

    private AccountCore accountCore;

}
