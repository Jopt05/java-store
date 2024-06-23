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
public class CreateProductDto {
    
    @Getter @Setter
    @NotBlank(message = "Name is required")
    private String name;
    
    @Getter @Setter
    @NotNull(message = "Price is required")
    private float price;
    
    @Getter @Setter
    @NotNull(message = "Name is required")
    private Long stock;
    
    @Getter @Setter
    private String imageUrl;
    
    @Getter @Setter
    @NotNull(message = "Category_id is required")
    private Integer category_id;
    
    public CreateProductDto toDto() {
        CreateProductDto dto = new CreateProductDto();
        dto.setCategory_id(category_id);
        dto.setImageUrl(imageUrl);
        dto.setName(name);
        dto.setStock(stock);
        dto.setPrice(price);
        return dto;
    }
}
