package com.tomas.demo.faculty.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Email
    @Size(min = 1, max = 150)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Teacher() {
    }

    public Teacher(UUID id, String name, String code, String email, String dni, boolean active) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.email = email;
        this.dni = dni;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
