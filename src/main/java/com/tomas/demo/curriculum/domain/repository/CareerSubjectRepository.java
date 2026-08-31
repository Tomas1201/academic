package com.tomas.demo.curriculum.domain.repository;

import com.tomas.demo.curriculum.domain.model.CareerSubject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CareerSubjectRepository {
    CareerSubject save(CareerSubject careerSubject);
    Optional<CareerSubject> findById(UUID id);
    List<CareerSubject> findByCareerId(UUID careerId);
    boolean existsByCareerIdAndSubjectId(UUID careerId, UUID subjectId);
    void deleteById(UUID id);
}
