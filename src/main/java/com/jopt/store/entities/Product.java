/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Jesús Puentes
 */
@Table(name = "products")
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter @Setter
    private Integer id;
    
    @Column(nullable = false)
    @Getter @Setter
    private String name;
    
    @Column(nullable = false)
    @Getter @Setter
    private float price;
    
    @Column(nullable = false)
    @Getter @Setter
    private Long stock;
    
    @Column(nullable = true, name = "image_url")
    @Getter @Setter
    private String imageUrl;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Getter @Setter
    private Category category;
    
    @ManyToMany(mappedBy = "products")
    private Set<Purchase> purchases;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    @Getter @Setter
    private Date createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Getter @Setter
    private Date updatedAt;
}
