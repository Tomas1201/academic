package com.tomas.demo.evaluation.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ExamDTO(
    UUID id,
    UUID teacherId,
    UUID studentId,
    UUID subjectId,
    UUID careerId,
    LocalDate examDate,
    Double grade,
    String notes,
    boolean active
) {}
