package com.tomas.demo.student.domain.repository;

import com.tomas.demo.student.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(UUID id);
    Optional<Student> findByDni(String dni);
    Optional<Student> findByEmail(String email);
    List<Student> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
