package com.kloia.atomikos.core.repository.account;

import com.kloia.atomikos.core.model.account.AccountCore;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface AccountCoreRepository extends CrudRepository<AccountCore, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<AccountCore> findById(Integer id);

}
