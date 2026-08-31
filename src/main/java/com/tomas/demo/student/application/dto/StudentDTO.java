package com.tomas.demo.student.application.dto;

import java.util.UUID;

public record StudentDTO(
    UUID id,
    String name,
    String email,
    String dni,
    int fileNumber,
    boolean active
) {}
