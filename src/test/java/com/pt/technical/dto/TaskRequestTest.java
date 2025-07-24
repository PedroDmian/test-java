package com.pt.technical.dto;

import com.pt.technical.infrastructure.dto.TaskRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskRequestTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTaskRequest() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Tarea importante");
        request.setDescription("Descripción válida");

        Set<ConstraintViolation<TaskRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testBlankNameIsInvalid() {
        TaskRequest request = new TaskRequest();
        request.setTitle("   ");
        request.setDescription("Descripción válida");

        Set<ConstraintViolation<TaskRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
    }

    @Test
    void testNullNameIsInvalid() {
        TaskRequest request = new TaskRequest();
        request.setTitle(null);
        request.setDescription("Descripción válida");

        Set<ConstraintViolation<TaskRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
    }

    @Test
    void testDescriptionTooLong() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Tarea");
        request.setDescription("a".repeat(2));

        Set<ConstraintViolation<TaskRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")));
    }
}
