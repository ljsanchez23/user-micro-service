package com.emazon.UserMicroservice.domain.model;

public class Role {
    private final Long id;
    private final String name;
    private final String description;

    public Role(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }
}
