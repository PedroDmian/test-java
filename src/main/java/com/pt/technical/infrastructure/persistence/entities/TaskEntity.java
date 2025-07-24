package com.pt.technical.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pt.technical.domain.models.Task;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "tasks")
@SQLRestriction("deleted_at IS NULL")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "is_completed", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isCompleted;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskLineEntity> lines = new ArrayList<>();

    public TaskEntity() {}

    public TaskEntity(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.isCompleted = task.isCompleted();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        this.deletedAt = task.getDeletedAt();
        this.lines = task.getLines() != null ? task.getLines().stream()
            .map(line -> {
                TaskLineEntity lineEntity = new TaskLineEntity(line);
                lineEntity.setTask(this);

                return lineEntity;
            })
            .toList() : new ArrayList<>();
    }

    public Task toModel() {
        return new Task(
            id,
            title,
            description,
            isCompleted,
            createdAt,
            updatedAt,
            deletedAt,
            lines.stream().map(TaskLineEntity::toModel).toList()
        );
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setDeletedAt() {
        this.deletedAt = LocalDateTime.now();
    }
}
