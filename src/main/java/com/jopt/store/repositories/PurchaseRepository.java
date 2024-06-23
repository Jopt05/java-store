package com.jopt.store.repositories;

import com.jopt.store.entities.Purchase;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    public List<Purchase> findByBuyer_Id(Integer buyerId);
}
