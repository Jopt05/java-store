/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Jesús Puentes
 */
@Data
public class CreateUserDto {
    
    @Getter @Setter
    @NotBlank(message = "Email is required")
    private String email;
    
    @Getter @Setter
    @NotBlank(message="Password is required")
    private String password;
    
    @Getter @Setter
    @NotBlank(message="FullName is required")
    private String fullName;
    
    
    public CreateUserDto toDto() {
        CreateUserDto dto = new CreateUserDto();
        dto.setEmail(email);
        dto.setFullName(fullName);
        dto.setPassword(password);
        return dto;
    }
}
