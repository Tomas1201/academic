package com.tomas.demo.features.career;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.tomas.demo.features.student.studentModel;

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

    @ManyToMany(mappedBy = "careers")
    private Set<studentModel> students = new HashSet<>();
}
