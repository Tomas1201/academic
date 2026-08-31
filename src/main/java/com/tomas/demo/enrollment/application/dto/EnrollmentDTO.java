package com.tomas.demo.enrollment.application.dto;

import com.tomas.demo.enrollment.domain.model.EnrollmentStatus;

import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentDTO(
    UUID id,
    UUID studentId,
    UUID subjectId,
    UUID careerId,
    LocalDate enrollmentDate,
    EnrollmentStatus status,
    boolean active
) {}
