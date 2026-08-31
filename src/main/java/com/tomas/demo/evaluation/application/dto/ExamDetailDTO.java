package com.tomas.demo.evaluation.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ExamDetailDTO(
    UUID id,
    UUID teacherId,
    String teacherName,
    UUID studentId,
    String studentName,
    String studentDni,
    int studentFileNumber,
    UUID subjectId,
    String subjectName,
    String subjectCode,
    UUID careerId,
    String careerName,
    LocalDate examDate,
    Double grade,
    String notes,
    boolean active
) {}
