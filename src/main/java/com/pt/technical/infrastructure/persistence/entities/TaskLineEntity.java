package com.pt.technical.infrastructure.persistence.entities;

import com.pt.technical.domain.models.TaskLine;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "task_lines")
public class TaskLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Setter
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    public TaskLineEntity() {}

    public TaskLineEntity(TaskLine taskLine) {
        this.name = taskLine.getName();
        this.description = taskLine.getDescription();
    }

    public TaskLine toModel() {
        return new TaskLine(
            id,
            name,
            description
        );
    }

}
