package com.jopt.store.dtos;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CategoryDto {
    
    @Getter @Setter
    @NotBlank
    private Integer id;

    @Getter @Setter
    @NotBlank
    private String name;

    @Getter @Setter
    private Date createdAt;

    @Getter @Setter
    private Date updatedAt;

    public CategoryDto(Integer id, String name, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
