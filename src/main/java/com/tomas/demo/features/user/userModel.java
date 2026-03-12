package com.tomas.demo.features.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.tomas.demo.features.career.careerModel;

@Entity
@Table(name = "users")
public class userModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "dni", nullable = false)
    private String dni;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "file", nullable = false)
    private int file;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "career_id", nullable = false)
    @ManyToMany
    @JoinTable(
          name = "student_career", 
          joinColumns = @JoinColumn(name = "student_id"), 
          inverseJoinColumns = @JoinColumn(name = "career_id")
        )
    private Set<careerModel> careers = new HashSet<>();
}
