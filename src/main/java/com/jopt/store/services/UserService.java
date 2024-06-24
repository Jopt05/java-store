/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.services;

import com.jopt.store.entities.User;
import com.jopt.store.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesús Puentes
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository usersRepository;
    
    public Optional<User> findByEmail(String email) {
        return this.usersRepository.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList();
        Iterable<User> usersIt = this.usersRepository.findAll();
        for (User user:usersIt) {
            usersList.add(user);
        }
        return usersList;
    }
    
    public void createUser(User user) {
        this.usersRepository.save(user);
    }
    
    public void updateUser(User user) {
        this.usersRepository.save(user);
    }
    
    public Optional<User> findById(Integer id) {
        return this.usersRepository.findById(id);
    }
    
}
