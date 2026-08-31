package com.tomas.demo.student.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

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
    @Size(min = 1, max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "file_number", nullable = false, unique = true)
    private int fileNumber;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Student() {
    }

    public Student(UUID id, String name, String email, String dni, String password, int fileNumber, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dni = dni;
        this.password = password;
        this.fileNumber = fileNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
