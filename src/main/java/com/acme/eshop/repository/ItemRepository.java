package com.acme.eshop.repository;

import com.acme.eshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Eleni on 5/13/2018.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
