package com.acme.eshop.repository;

import com.acme.eshop.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address save(Address address);
    void delete(Address address);
}
