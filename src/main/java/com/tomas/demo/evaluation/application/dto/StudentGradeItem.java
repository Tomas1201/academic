package com.tomas.demo.evaluation.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record StudentGradeItem(
    @NotNull UUID studentId,
    @NotNull @DecimalMin("0.0") @DecimalMax("10.0") Double grade,
    @Size(max = 255) String notes
) {}
