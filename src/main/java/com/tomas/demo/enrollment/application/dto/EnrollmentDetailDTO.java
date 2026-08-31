package com.tomas.demo.enrollment.application.dto;

import com.tomas.demo.enrollment.domain.model.EnrollmentStatus;

import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentDetailDTO(
    UUID id,
    UUID studentId,
    String studentName,
    String studentDni,
    int studentFileNumber,
    UUID subjectId,
    String subjectName,
    String subjectCode,
    UUID careerId,
    String careerName,
    LocalDate enrollmentDate,
    EnrollmentStatus status,
    boolean active
) {}
