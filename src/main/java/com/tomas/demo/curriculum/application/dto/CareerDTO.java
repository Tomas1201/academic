package com.tomas.demo.curriculum.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CareerDTO(
    UUID id,
    @NotBlank @Size(min = 1, max = 100) String name,
    @NotBlank @Size(min = 1, max = 50) String code,
    @NotBlank @Size(min = 1, max = 255) String description,
    boolean active
) {}
