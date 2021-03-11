package com.kloia.atomikos.accountservice.repository.account;

import com.kloia.atomikos.accountservice.model.account.Account;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<Account> findById(Integer id);

}
