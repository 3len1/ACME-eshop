package com.acme.eshop.repository;

import com.acme.eshop.domain.Address;
import com.acme.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserId(Long userId);
    Address save(Address address);
    void delete(Address address);
}
