/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreateCategoryDto;
import com.jopt.store.dtos.CategoryDto;
import com.jopt.store.entities.Category;
import com.jopt.store.entities.User;
import com.jopt.store.response.BaseResponse;
import com.jopt.store.services.CategoriesService;
import com.jopt.store.services.UserService;
import com.jopt.store.utils.ProjectUtils;

import jakarta.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class CategoriesController {
    
    @Autowired
    CategoriesService categoriesService;
    
    @Autowired
    UserService usersService;
    
    @Autowired
    ProjectUtils projectUtils;
    
    private static final String CATEGORIES_OBTAINED = "Categories obtained correctly.";
    private static final String CATEGORY_CREATED = "Category created correctly.";
    
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<Object>> getAll() {
        List<CategoryDto> catList = categoriesService.getAllCategories();
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(CATEGORIES_OBTAINED)
                .setPayload(catList)
                .build();
    }
    
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Object>> post(@Valid @RequestBody CreateCategoryDto createCatDto) throws AccessDeniedException {
        User user = projectUtils.getUserFromAuth();
        CreateCategoryDto dto = createCatDto.toDto();
        Category category = new Category();
        category.setCreator(user);
        category.setName(dto.getName());
        categoriesService.createCategory(category);
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(CATEGORY_CREATED)
                .setSuccess(true).build();
    }
}
