package com.tomas.demo.curriculum.domain.repository;

import com.tomas.demo.curriculum.domain.model.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository {
    Subject save(Subject subject);
    Optional<Subject> findById(UUID id);
    List<Subject> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
