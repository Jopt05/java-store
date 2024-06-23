/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreateUserDto;
import com.jopt.store.dtos.LoginDto;
import com.jopt.store.entities.User;
import com.jopt.store.services.AuthService;
import com.jopt.store.services.JwtService;
import com.jopt.store.services.UserService;

import jakarta.validation.Valid;
import java.util.HashMap;
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
@RequestMapping("/api/v1/auth")
@RestController
public class UsersController {
    
    @Autowired
    UserService usersService;
    
    @Autowired
    AuthService authService;
    
    @Autowired
    JwtService jwtService;
    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return usersService.getAllUsers();
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity post(@Valid @RequestBody CreateUserDto createUserDto) {
        authService.signup(createUserDto);
        
        HashMap<String, String> body = new HashMap();
        body.put("msg", "Registrado correctamente");
        return ResponseEntity.ok(body);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity post(@Valid @RequestBody LoginDto loginDto) {
        User user = authService.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(user);
        
        HashMap<String, String> body = new HashMap();
        body.put("token", jwtToken);
        body.put("msg", "Logueado correctamente");
        
        return ResponseEntity.ok(body);
    }
    
}
