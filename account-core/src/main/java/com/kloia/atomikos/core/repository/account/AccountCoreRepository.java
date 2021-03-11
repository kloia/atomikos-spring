package com.kloia.atomikos.core.repository.account;

import com.kloia.atomikos.core.model.account.AccountCore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCoreRepository extends CrudRepository<AccountCore, Integer> {
}
