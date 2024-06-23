/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jopt.store.repositories;

import com.jopt.store.entities.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jesús Puentes
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    public List<Product> findByCategory_Id(Integer categoryId);
}
