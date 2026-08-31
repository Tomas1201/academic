package com.tomas.demo.enrollment.domain.repository;

import com.tomas.demo.enrollment.domain.model.Enrollment;
import com.tomas.demo.enrollment.domain.model.EnrollmentStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findById(UUID id);
    List<Enrollment> findAll();
    List<Enrollment> findByStudentId(UUID studentId);
    List<Enrollment> findBySubjectId(UUID subjectId);
    List<Enrollment> findByCareerId(UUID careerId);
    Optional<Enrollment> findByStudentIdAndSubjectIdAndCareerIdAndStatus(UUID studentId, UUID subjectId, UUID careerId, EnrollmentStatus status);
    boolean existsByStudentIdAndSubjectIdAndCareerIdAndStatus(UUID studentId, UUID subjectId, UUID careerId, EnrollmentStatus status);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
