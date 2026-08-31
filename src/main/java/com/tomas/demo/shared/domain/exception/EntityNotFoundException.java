package com.tomas.demo.shared.domain.exception;

import java.util.UUID;

public class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String entityName, UUID id) {
        super(String.format("%s not found with id: %s", entityName, id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
