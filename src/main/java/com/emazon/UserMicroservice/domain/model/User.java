package com.emazon.UserMicroservice.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private Integer idDocument;
    private String phone;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private Role role;

    public User(Long id, String name, String lastName, Integer idDocument, String phone, LocalDate dateOfBirth,
                String email, String password, Role role){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getIdDocument() {
        return idDocument;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole (Role role){
        this.role = role;
    }

    public Long getId(){
        return this.id;
    }


}
