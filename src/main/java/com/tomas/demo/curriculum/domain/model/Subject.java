package com.tomas.demo.curriculum.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "subjects")
public class Subject {

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
    @Size(min = 1, max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "credits", nullable = false)
    private int credits;

    @NotNull
    @Column(name = "semester", nullable = false)
    private int semester;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;

    // Intra-domain relationship with CareerSubject
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CareerSubject> careerSubjects = new HashSet<>();

    public Subject() {
    }

    public Subject(UUID id, String name, String code, String description, int credits, int semester, boolean active) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.credits = credits;
        this.semester = semester;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<CareerSubject> getCareerSubjects() {
        return careerSubjects;
    }

    public void setCareerSubjects(Set<CareerSubject> careerSubjects) {
        this.careerSubjects = careerSubjects;
    }
}
