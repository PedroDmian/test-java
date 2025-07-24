package com.pt.technical.dto;

import com.pt.technical.infrastructure.dto.TaskLineRequest;
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

public class TaskLineRequestTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTaskRequest() {
        TaskLineRequest request = new TaskLineRequest();
        request.setName("Tarea importante 1");
        request.setDescription("Descripción válida");

        Set<ConstraintViolation<TaskLineRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testBlankNameIsInvalid() {
        TaskLineRequest request = new TaskLineRequest();
        request.setName("   ");
        request.setDescription("Descripción válida");

        Set<ConstraintViolation<TaskLineRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void testNullNameIsInvalid() {
        TaskLineRequest request = new TaskLineRequest();
        request.setName(null);
        request.setDescription("Descripción válida");

        Set<ConstraintViolation<TaskLineRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void testNullDescriptionIsValid() {
        TaskLineRequest request = new TaskLineRequest();
        request.setName("Tarea");
        request.setDescription(null);

        Set<ConstraintViolation<TaskLineRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }
}
