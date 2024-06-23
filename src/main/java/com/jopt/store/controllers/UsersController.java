/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreateUserDto;
import com.jopt.store.entities.User;
import com.jopt.store.services.UserService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesús Puentes
 */
@RequestMapping("/api/v1")
@RestController
public class UsersController {
    
    @Autowired
    UserService usersService;
    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return usersService.getAllUsers();
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity post(@Valid @RequestBody CreateUserDto createUserDto) {
        CreateUserDto dto = createUserDto.toDto();
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setAdmin(dto.isAdmin() || false);
        user.setFullName(dto.getFullName());
        user.setPassword(dto.getPassword());
        usersService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
