/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.controllers;

import com.jopt.store.dtos.CreateUserDto;
import com.jopt.store.dtos.LoginDto;
import com.jopt.store.dtos.UpdateUserDto;
import com.jopt.store.entities.User;
import com.jopt.store.exceptions.PermissionsException;
import com.jopt.store.response.BaseResponse;
import com.jopt.store.services.AuthService;
import com.jopt.store.services.JwtService;
import com.jopt.store.services.UserService;
import com.jopt.store.utils.ProjectUtils;

import jakarta.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@RequestMapping("/api/v1/auth")
@RestController
public class UsersController {
    
    @Autowired
    UserService usersService;
    
    @Autowired
    AuthService authService;
    
    @Autowired
    JwtService jwtService;
    
    @Autowired
    ProjectUtils projectUtils;
    
    public static final String CREATED_USER = "User created correctly";
    public static final String LOGGED_USER = "User logged correctly";
    public static final String UPDATED_USER = "User was updated correctly";
    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return usersService.getAllUsers();
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Object>> post(@Valid @RequestBody CreateUserDto createUserDto) {
        authService.signup(createUserDto);
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(CREATED_USER)
                .setSuccess(true).build();
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Object>> post(@Valid @RequestBody LoginDto loginDto) {
        User user = authService.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(user);
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(LOGGED_USER)
                .setPayload(jwtToken).setSuccess(true).build();
    }
    
    @RequestMapping(value = "/users/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<BaseResponse<Object>> put(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserDto updateUserDto
    ) throws AccessDeniedException, PermissionsException {
        User user = projectUtils.getUserFromAuth();
        if( user.getIsAdmin()== false ) throw new PermissionsException("You are not authorized to perform this action");
        Optional<User> updateUser = usersService.findById(id);
        if( updateUser.get() == null ) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
            );
        }
        UpdateUserDto dto = updateUserDto.toDto();
        updateUser.get().setIsAdmin(dto.getIsAdmin() == null ? updateUser.get().getIsAdmin() : dto.getIsAdmin());
        updateUser.get().setFullName(dto.getFullName() == null ? updateUser.get().getFullName() : dto.getFullName());
        usersService.updateUser(updateUser.get());
        
        return new BaseResponse.BaseResponseBuilder<>()
                .setHttpStatus(HttpStatus.OK)
                .setMessage(UPDATED_USER)
                .setSuccess(true).build();
    }
    
}
