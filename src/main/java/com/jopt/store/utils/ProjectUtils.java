/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.utils;

import com.jopt.store.entities.User;
import java.nio.file.AccessDeniedException;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jesús Puentes
 */
@Component
public class ProjectUtils {
    
    public User getUserFromAuth() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Auth" + authentication);
        if( authentication == null || authentication.getPrincipal() == "anonymousUser" ) throw new AccessDeniedException("Error");
        User loggedUser = (User) authentication.getPrincipal();
        return loggedUser;
    }
    
}
