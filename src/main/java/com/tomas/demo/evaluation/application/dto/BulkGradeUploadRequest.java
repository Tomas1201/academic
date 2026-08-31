package com.tomas.demo.evaluation.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record BulkGradeUploadRequest(
    @NotNull UUID teacherId,
    @NotNull UUID subjectId,
    @NotNull UUID careerId,
    LocalDate examDate,
    @NotEmpty List<@Valid StudentGradeItem> grades
) {}
