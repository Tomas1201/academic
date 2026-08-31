package com.tomas.demo.curriculum.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddSubjectToCareerRequest(
    @NotNull UUID subjectId,
    @NotNull @Min(1) Integer semester,
    @NotNull @Min(1) Integer studyYear,
    boolean mandatory
) {}
