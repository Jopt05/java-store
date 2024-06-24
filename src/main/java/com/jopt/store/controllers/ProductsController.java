/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreateProductDto;
import com.jopt.store.entities.Category;
import com.jopt.store.entities.Product;
import com.jopt.store.entities.User;
import com.jopt.store.response.BaseResponse;
import com.jopt.store.services.CategoriesService;
import com.jopt.store.services.ProductsService;
import com.jopt.store.utils.ProjectUtils;

import jakarta.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ProductsController {

    @Autowired
    ProductsService productsService;
    
    @Autowired
    CategoriesService categoriesService;
    
    @Autowired
    ProjectUtils projectUtils;
    
    private static final String PRODUCTS_OBTAINED = "Products obtained correctly.";
    private static final String PRODUCT_CREATED = "Product created correctly";
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<Object>> getAll() {
        List<Product> productsList = productsService.getAllProducts();
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(PRODUCTS_OBTAINED)
                .setPayload(productsList)
                .setSuccess(true).build();
    }
    
    @RequestMapping(value = "/category/{id}/products", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<Object>> getProductsByCategoryId(@PathVariable Integer id) {
        List<Product> productsList = productsService.getProductsByCategoryId(id);
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(PRODUCTS_OBTAINED)
                .setPayload(productsList)
                .setSuccess(true).build();
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Object>> post(@Valid @RequestBody CreateProductDto createProductDto) throws AccessDeniedException {
        User user = projectUtils.getUserFromAuth();
        CreateProductDto dto = createProductDto;
        Optional<Category> category = categoriesService.getCategoryById(dto.getCategory_id());
        if( category == null ) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Category not found"
            );
        } else {
            Product product = new Product();
            product.setCategory(category.get());
            product.setImageUrl(dto.getImageUrl());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());
            productsService.createProduct(product);
            
            return new BaseResponse.BaseResponseBuilder<>()
                    .setHttpStatus(HttpStatus.OK)
                    .setSuccess(true)
                    .setMessage(PRODUCT_CREATED).build();
        }
    }
    
}
