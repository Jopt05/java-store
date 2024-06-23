/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.entities.Purchase;
import com.jopt.store.services.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesús Puentes
 */
@RequestMapping("/api/v1")
@RestController
public class PurchasesController {
    
    @Autowired
    PurchaseService purchasesService;
    
    @RequestMapping(value = "/users/{id}/purchases", method = RequestMethod.GET)
    public List<Purchase> get(@PathVariable Integer id) {
        return purchasesService.getUserPurchases(id);
    }
    
    
}
