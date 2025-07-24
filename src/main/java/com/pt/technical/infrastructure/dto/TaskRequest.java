package com.pt.technical.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TaskRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Size(min = 2, message = "Title must be at least 1 character long")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5, message = "Title must be at least 1 character long")
    private String description;

    @NotNull
    private boolean isCompleted;

    private List<TaskLineRequest> lines;
}
