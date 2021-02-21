package com.kloia.atomikos.dto;

import com.kloia.atomikos.model.account.Account;
import com.kloia.atomikos.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAccountCreateResponseDto {

    private Customer customer;

    private Account account;

}
