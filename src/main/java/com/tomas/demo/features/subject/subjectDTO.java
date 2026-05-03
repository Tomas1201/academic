package com.tomas.demo.features.subject;

import java.util.UUID;

public record subjectDTO(UUID id, String name, String code, String description, int credits, int semester) {
    
}
