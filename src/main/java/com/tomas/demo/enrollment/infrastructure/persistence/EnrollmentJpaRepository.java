package com.tomas.demo.enrollment.infrastructure.persistence;

import com.tomas.demo.enrollment.domain.model.Enrollment;
import com.tomas.demo.enrollment.domain.model.EnrollmentStatus;
import com.tomas.demo.enrollment.domain.repository.EnrollmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, UUID>, EnrollmentRepository {
    @Override
    List<Enrollment> findByStudentId(UUID studentId);

    @Override
    List<Enrollment> findBySubjectId(UUID subjectId);

    @Override
    List<Enrollment> findByCareerId(UUID careerId);

    @Override
    Optional<Enrollment> findByStudentIdAndSubjectIdAndCareerIdAndStatus(UUID studentId, UUID subjectId, UUID careerId, EnrollmentStatus status);

    @Override
    boolean existsByStudentIdAndSubjectIdAndCareerIdAndStatus(UUID studentId, UUID subjectId, UUID careerId, EnrollmentStatus status);
}
