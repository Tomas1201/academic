package com.tomas.demo.attendance.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AttendanceDTO(
    UUID id,
    UUID studentId,
    UUID subjectId,
    LocalDate date,
    int value,
    boolean active
) {}
