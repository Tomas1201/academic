package com.tomas.demo.curriculum.domain.repository;

import com.tomas.demo.curriculum.domain.model.Career;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CareerRepository {
    Career save(Career career);
    Optional<Career> findById(UUID id);
    List<Career> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
