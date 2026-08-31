package com.tomas.demo.faculty.domain.repository;

import com.tomas.demo.faculty.domain.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository {
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(UUID id);
    Optional<Teacher> findByCode(String code);
    Optional<Teacher> findByEmail(String email);
    List<Teacher> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
