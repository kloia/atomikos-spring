package com.kloia.atomikos.accountservice.dto;

import com.kloia.atomikos.accountservice.model.account.Account;
import com.kloia.atomikos.core.model.account.AccountCore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransactionResponseDto {

    private Account account;

    private AccountCore accountCore;

}
