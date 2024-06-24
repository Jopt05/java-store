/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jesús Puentes
 */
@Data
public class CreateCategoryDto {
    
    @Getter @Setter
    @NotBlank(message = "Name is required")
    private String name;
    
    public CreateCategoryDto toDto() {
        CreateCategoryDto dto = new CreateCategoryDto();
        dto.setName(name);
        return dto;
    }
}
