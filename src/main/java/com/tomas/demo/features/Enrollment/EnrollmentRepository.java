package com.tomas.demo.features.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentModel, UUID> {
}
