package com.tomas.demo.curriculum.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "careers")
public class Career {

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
    @Column(name = "active", nullable = false)
    private boolean active = true;

    // Intra-domain Hibernate relationship with CareerSubject (Study plan)
    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CareerSubject> studyPlan = new HashSet<>();

    public Career() {
    }

    public Career(UUID id, String name, String code, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.active = active;
    }

    public void addSubjectToPlan(Subject subject, int semester, int year, boolean mandatory) {
        CareerSubject careerSubject = new CareerSubject(this, subject, semester, year, mandatory);
        studyPlan.add(careerSubject);
    }

    public void removeSubjectFromPlan(Subject subject) {
        studyPlan.removeIf(cs -> cs.getSubject().equals(subject));
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<CareerSubject> getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(Set<CareerSubject> studyPlan) {
        this.studyPlan = studyPlan;
    }
}
