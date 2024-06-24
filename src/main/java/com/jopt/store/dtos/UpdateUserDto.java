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
 * @author Lenovo
 */
@Data
public class UpdateUserDto {
    
    @Getter @Setter
    private String fullName;
    
    @Getter @Setter
    private Boolean isAdmin;
    
    public UpdateUserDto toDto() {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setFullName(fullName);
        dto.setIsAdmin(isAdmin);
        return dto;
    }
    
}
