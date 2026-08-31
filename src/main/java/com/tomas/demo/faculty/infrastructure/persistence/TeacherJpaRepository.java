package com.tomas.demo.faculty.infrastructure.persistence;

import com.tomas.demo.faculty.domain.model.Teacher;
import com.tomas.demo.faculty.domain.repository.TeacherRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherJpaRepository extends JpaRepository<Teacher, UUID>, TeacherRepository {
    @Override
    Optional<Teacher> findByCode(String code);

    @Override
    Optional<Teacher> findByEmail(String email);
}
