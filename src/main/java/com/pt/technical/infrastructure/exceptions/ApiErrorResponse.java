package com.pt.technical.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Any;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ApiErrorResponse {
    private int statusCode;

    private String message;

    private List<String> errors;

    private String details;

    private LocalDateTime timestamp;
}
