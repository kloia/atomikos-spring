package com.kloia.atomikos.accountservice.repository.account;

import com.kloia.atomikos.accountservice.model.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
