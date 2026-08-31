package com.tomas.demo.curriculum.infrastructure.persistence;

import com.tomas.demo.curriculum.domain.model.Subject;
import com.tomas.demo.curriculum.domain.repository.SubjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectJpaRepository extends JpaRepository<Subject, UUID>, SubjectRepository {
}
