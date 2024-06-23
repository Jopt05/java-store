/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.dtos;

import jakarta.validation.constraints.NotNull;
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
    
    
    
}
