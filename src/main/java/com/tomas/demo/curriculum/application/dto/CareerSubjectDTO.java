package com.tomas.demo.curriculum.application.dto;

import java.util.UUID;

public record CareerSubjectDTO(
    UUID id,
    UUID careerId,
    String careerName,
    UUID subjectId,
    String subjectName,
    String subjectCode,
    int semester,
    int studyYear,
    boolean mandatory
) {}
