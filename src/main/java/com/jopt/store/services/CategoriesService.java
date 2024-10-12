/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.services;

import com.jopt.store.entities.Category;
import com.jopt.store.repositories.CategoryRepository;
import com.jopt.store.dtos.CategoryDto;

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

    private CategoryDto mapToDto(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getCreatedAt(), category.getUpdatedAt());
    }
    
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoriesList = new ArrayList();
        Iterable<Category> categoriesIterable = categoriesRepository.findAll();
        for(Category categorie:categoriesIterable) {
            categoriesList.add(this.mapToDto(categorie));
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
