package com.tomas.demo.features.teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "teacher")
public class teacherModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Size(min = 1, max = 50)
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
    private boolean active;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}
