package com.kloia.atomikos.customerservice.repository.customeraddress;

import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddress;
import com.kloia.atomikos.customerservice.model.customeraddress.CustomerAddressId;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, CustomerAddressId> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<CustomerAddress> findById(CustomerAddressId customerAddressId);

}
