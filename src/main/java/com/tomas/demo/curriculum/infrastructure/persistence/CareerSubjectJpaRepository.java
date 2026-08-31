package com.tomas.demo.curriculum.infrastructure.persistence;

import com.tomas.demo.curriculum.domain.model.CareerSubject;
import com.tomas.demo.curriculum.domain.repository.CareerSubjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CareerSubjectJpaRepository extends JpaRepository<CareerSubject, UUID>, CareerSubjectRepository {
    @Override
    List<CareerSubject> findByCareerId(UUID careerId);

    @Override
    boolean existsByCareerIdAndSubjectId(UUID careerId, UUID subjectId);
}
