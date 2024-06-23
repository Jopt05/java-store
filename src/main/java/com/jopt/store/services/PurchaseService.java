package com.jopt.store.services;

import com.jopt.store.entities.Purchase;
import com.jopt.store.repositories.PurchaseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    
    @Autowired
    PurchaseRepository purchasesRepository;
    
    public void createPurchase(Purchase purchase) {
        purchasesRepository.save(purchase);
    }
    
    public List<Purchase> getUserPurchases(Integer userId) {
        return purchasesRepository.findByBuyer_Id(userId);
    }

}
