package com.tomas.demo.evaluation.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterGradeCommand(
    @NotNull UUID teacherId,
    @NotNull UUID studentId,
    @NotNull UUID subjectId,
    @NotNull UUID careerId,
    @NotNull @DecimalMin("0.0") @DecimalMax("10.0") Double grade,
    LocalDate examDate,
    @Size(max = 255) String notes
) {}
