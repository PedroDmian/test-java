package com.pt.technical.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskLineRequest {
    @NotBlank
    private String name;

    private String description;
}
