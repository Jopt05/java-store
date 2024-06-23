/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreateCategoryDto;
import com.jopt.store.entities.Category;
import com.jopt.store.entities.User;
import com.jopt.store.services.CategoriesService;
import com.jopt.store.services.UserService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CategoriesController {
    
    @Autowired
    CategoriesService categoriesService;
    
    @Autowired
    UserService usersService;
    
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Category> getAll() {
        return categoriesService.getAllCategories();
    }
    
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity post(@Valid @RequestBody CreateCategoryDto createCatDto) {
        CreateCategoryDto dto = createCatDto.toDto();
        Optional<User> user = usersService.findById(dto.getCreator_id());
        if( user == null ) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
            );
        } else {
            Category category = new Category();
            category.setCreator(user.get());
            category.setName(dto.getName());
            categoriesService.createCategory(category);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
