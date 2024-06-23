/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jesús Puentes
 */
@Data
public class CreatePurchaseDto {
    
    @Getter @Setter
    @NotNull(message = "Buyer_id is required")
    private Integer buyer_id;
    
    @Getter @Setter
    @NotNull(message = "Products ist required")
    @NotEmpty(message = "Products must have at least one item.")
    private String[] products_id;
    
    public CreatePurchaseDto toDto() {
        CreatePurchaseDto dto = new CreatePurchaseDto();
        dto.setBuyer_id(buyer_id);
        dto.setProducts_id(products_id);
        return dto;
    }
    
}
