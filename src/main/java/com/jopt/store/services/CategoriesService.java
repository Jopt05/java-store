/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.services;

import com.jopt.store.entities.Category;
import com.jopt.store.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesús Puentes
 */
@Service
public class CategoriesService {
    
    @Autowired
    CategoryRepository categoriesRepository;
    
    public List<Category> getAllCategories() {
        List<Category> categoriesList = new ArrayList();
        Iterable<Category> catIter = categoriesRepository.findAll();
        for (Category cat:catIter) {
            categoriesList.add(cat);
        }
        return categoriesList;
    }
    
    public void createCategory(Category category) {
        categoriesRepository.save(category);
    }
    
    public Optional<Category> getCategoryById(Integer id) {
        return categoriesRepository.findById(id);
    }
    
}
