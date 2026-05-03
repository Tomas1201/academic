package com.tomas.demo.features.career;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.tomas.demo.features.student.studentModel;
import com.tomas.demo.features.subject.subjectModel;

@Entity
@Table(name = "career")
public class careerModel {

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
    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToMany(mappedBy = "careers")
    private Set<studentModel> students = new HashSet<>();

    @ManyToMany
    @JoinTable(
          name = "career_subject", 
          joinColumns = @JoinColumn(name = "career_id"), 
          inverseJoinColumns = @JoinColumn(name = "subject_id")
        )
    private Set<subjectModel> subjects = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<studentModel> getStudents() {
        return students;
    }

    public void setStudents(Set<studentModel> students) {
        this.students = students;
    }

    public Set<subjectModel> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<subjectModel> subjects) {
        this.subjects = subjects;
    }

    public careerModel(int id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public careerModel() {
    }
}
