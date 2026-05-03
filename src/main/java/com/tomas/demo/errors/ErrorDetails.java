package com.tomas.demo.errors;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String errorCode; // Opcional: para códigos de error internos (ej: "USER_001")

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }       

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
