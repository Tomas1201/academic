package com.tomas.demo.features.exam;

import java.time.LocalDate;
import java.util.UUID;

public record examDTO(
    UUID id,
    UUID studentId,
    UUID subjectId,
    LocalDate examDate,
    Double grade,
    String notes
) {}
