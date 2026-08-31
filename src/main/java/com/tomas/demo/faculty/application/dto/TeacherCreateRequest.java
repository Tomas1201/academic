package com.tomas.demo.faculty.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeacherCreateRequest(
    @NotBlank @Size(min = 1, max = 100) String name,
    @NotBlank @Size(min = 1, max = 50) String code,
    @NotBlank @Email @Size(min = 1, max = 150) String email,
    @NotBlank @Size(min = 1, max = 20) String dni
) {}
