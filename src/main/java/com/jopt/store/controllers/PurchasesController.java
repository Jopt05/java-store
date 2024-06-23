/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreatePurchaseDto;
import com.jopt.store.entities.Product;
import com.jopt.store.entities.Purchase;
import com.jopt.store.entities.User;
import com.jopt.store.services.ProductsService;
import com.jopt.store.services.PurchaseService;
import com.jopt.store.services.UserService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Jesús Puentes
 */
@RequestMapping("/api/v1")
@RestController
public class PurchasesController {
    
    @Autowired
    PurchaseService purchasesService;
    
    @Autowired
    UserService usersService;
    
    @Autowired
    ProductsService productsService;
    
    @RequestMapping(value = "/users/{id}/purchases", method = RequestMethod.GET)
    public List<Purchase> get(@PathVariable Integer id) {
        List<Purchase> purchasesList = purchasesService.getUserPurchases(id);
        return purchasesList;
    }
    
    @RequestMapping(value = "/users/{id}/purchases", method = RequestMethod.POST)
    public String post(
            @PathVariable Integer id, 
            @Valid @RequestBody CreatePurchaseDto createPurchaseDto) {
        CreatePurchaseDto dto = createPurchaseDto.toDto();
        Optional<User> user = usersService.findById(dto.getBuyer_id());
        if( user == null ) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
            );
        } else {
            List<Product> productsList = new ArrayList();
            Purchase purchase = new Purchase();
            purchase.setBuyer(user.get());
            for( String product_id:dto.getProducts_id() ) {
                Optional<Product> product = productsService.getProductById(Integer.valueOf(product_id));
                if( product != null ) {
                    productsList.add(product.get());
                }
            }
            purchase.setProducts(productsList);
            purchasesService.createPurchase(purchase);
            return "Completado";
        }
    }
    
}
