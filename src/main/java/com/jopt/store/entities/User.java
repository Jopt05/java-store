/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Jesús Puentes
 */
@Table(name = "users")
@Entity
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter @Setter
    private Integer id;
    
    @Column(nullable = false, name = "full_name")
    @Getter @Setter
    private String fullName;
    
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String email;
    
    @Column(nullable = false)
    @Getter @Setter
    @JsonIgnore
    private String password;
    
    @Column(nullable = false, name = "is_admin")
    @Getter @Setter
    @JsonIgnore
    private boolean isAdmin;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    @Getter @Setter
    private Date createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Getter @Setter
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
