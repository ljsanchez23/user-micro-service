package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity;

import com.emazon.UserMicroservice.adapters.util.AdapConstants;
import jakarta.persistence.*;

@Entity
@Table(name = AdapConstants.ROLE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    public RoleEntity() {
    }

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id){
        this.id = id;
    }
}
