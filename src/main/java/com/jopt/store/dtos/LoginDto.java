/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jesús Puentes
 */
@Data
public class LoginDto {
    
    @Getter @Setter
    @NotBlank(message = "Email is required")
    private String email;
    
    @Getter @Setter
    @NotBlank(message = "password is required")
    private String password;
    
    public LoginDto toDto() {
        LoginDto dto = new LoginDto();
        dto.setEmail(email);
        dto.setPassword(password);
        return dto;
    }
    
}
