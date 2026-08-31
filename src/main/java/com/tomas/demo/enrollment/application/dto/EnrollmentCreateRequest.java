package com.tomas.demo.enrollment.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnrollmentCreateRequest(
    @NotNull UUID studentId,
    @NotNull UUID subjectId,
    @NotNull UUID careerId
) {}
