package com.tomas.demo.features.subject;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import com.tomas.demo.features.career.careerModel;

@Entity
@Table(name = "subject")
public class subjectModel {
    
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


    
    @ManyToMany(mappedBy = "subjects")
    private Set<careerModel> careers = new HashSet<>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Set<careerModel> getCareers() {
        return this.careers;
    }

    public void setCareers(Set<careerModel> careers) {
        this.careers = careers;
    }
}
