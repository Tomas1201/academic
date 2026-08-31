package com.tomas.demo.faculty.application.dto;

import java.util.UUID;

public record TeacherDTO(
    UUID id,
    String name,
    String code,
    String email,
    String dni,
    boolean active
) {}
