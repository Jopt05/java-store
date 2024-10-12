/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.services;

import com.jopt.store.entities.Product;
import com.jopt.store.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesús Puentes
 */
@Service
public class ProductsService {
    
    @Autowired
    ProductRepository productsRepository;
    
    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList();
        Iterable<Product> prodIter = productsRepository.findAll();
        for (Product prod:prodIter) {
            productsList.add(prod);
        }
        return productsList;
    }
    
    public void createProduct(Product product) {
        productsRepository.save(product);
    }
    
    public Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable) {
        return productsRepository.findAllByCategory_Id(categoryId, pageable);
    }
    
    public Optional<Product> getProductById(Integer id) {
        return productsRepository.findById(id);
    }
    
    public void decreaseProductStock(Product product) {
        product.setStock( product.getStock() - 1 );
        productsRepository.save(product);
    }
}
