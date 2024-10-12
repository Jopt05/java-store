/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreatePurchaseDto;
import com.jopt.store.entities.Product;
import com.jopt.store.entities.Purchase;
import com.jopt.store.entities.User;
import com.jopt.store.exceptions.OutOfStockException;
import com.jopt.store.response.BaseResponse;
import com.jopt.store.services.ProductsService;
import com.jopt.store.services.PurchaseService;
import com.jopt.store.services.UserService;
import com.jopt.store.utils.ProjectUtils;
import jakarta.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin()
@RequestMapping("/api/v1")
@RestController
public class PurchasesController {
    
    @Autowired
    PurchaseService purchasesService;
    
    @Autowired
    UserService usersService;
    
    @Autowired
    ProductsService productsService;
    
    @Autowired
    ProjectUtils projectUtils;
    
    private static final String PURCHASES_OBTAINED = "Purchases obtained correctly";
    private static final String PURCHASE_CREATED = "Purchase created correctly";
    
    @RequestMapping(value = "/users/purchases", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<Object>> get() throws AccessDeniedException {
        User user = projectUtils.getUserFromAuth();
        System.out.println("User" + user);
        List<Purchase> purchasesList = purchasesService.getUserPurchases(user.getId());
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setSuccess(true)
                .setPayload(purchasesList)
                .setHttpStatus(HttpStatus.OK)
                .setMessage(PURCHASES_OBTAINED).build();
    }
    
    @RequestMapping(value = "/users/purchases", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Object>> post(
            @Valid @RequestBody CreatePurchaseDto createPurchaseDto) throws AccessDeniedException, OutOfStockException {
        User user = projectUtils.getUserFromAuth();
        CreatePurchaseDto dto = createPurchaseDto.toDto();
        List<Product> productsList = new ArrayList();
        Purchase purchase = new Purchase();
        purchase.setBuyer(user);
        for( String product_id:dto.getProducts_id() ) {
            Optional<Product> product = productsService.getProductById(Integer.valueOf(product_id));
            if( !product.isEmpty() ) {
                Product dbProduct  = product.get();
                Long stock = dbProduct.getStock();
                if( stock == 0 ) {
                    throw new OutOfStockException("Product " + dbProduct.getName() + " is out of stock");
                };
                productsService.decreaseProductStock(dbProduct);
                productsList.add(product.get());
            }
        }
        purchase.setProducts(productsList);
        purchasesService.createPurchase(purchase);
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setSuccess(true)
                .setMessage(PURCHASE_CREATED)
                .setHttpStatus(HttpStatus.OK).build();
    }
    
}
