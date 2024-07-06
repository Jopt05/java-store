/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jopt.store.repositories;

import com.jopt.store.entities.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jesús Puentes
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Page<Product> findAllByCategory_Id(Integer categoryId, Pageable pageable);
}
