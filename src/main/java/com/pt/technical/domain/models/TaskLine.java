package com.pt.technical.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TaskLine {
    private Long id;

    private String name;

    private String description;
}
