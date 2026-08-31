package com.tomas.demo.student.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentCreateRequest(
    @NotBlank @Size(min = 1, max = 100) String name,
    @NotBlank @Email @Size(min = 1, max = 150) String email,
    @NotBlank @Size(min = 1, max = 20) String dni,
    @NotBlank @Size(min = 4, max = 50) String password,
    @NotNull Integer fileNumber
) {}
