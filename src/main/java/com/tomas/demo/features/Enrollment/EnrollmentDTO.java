package com.tomas.demo.features.Enrollment;

import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentDTO(UUID id, UUID studentId, UUID subjectId, UUID careerId, LocalDate enrollmentDate) {
}