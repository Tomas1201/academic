package com.tomas.demo.attendance.application.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AttendanceCreateRequest(
    @NotNull UUID studentId,
    @NotNull UUID subjectId,
    LocalDate date,
    @NotNull Integer value
) {}
