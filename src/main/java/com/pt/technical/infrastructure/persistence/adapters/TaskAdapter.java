package com.pt.technical.infrastructure.persistence.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.pt.technical.infrastructure.persistence.repository.TaskJpaRepository;
import com.pt.technical.infrastructure.persistence.entities.TaskEntity;
import com.pt.technical.domain.repository.TaskRepository;
import com.pt.technical.domain.models.Task;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskAdapter implements TaskRepository {
    private final TaskJpaRepository jpa;

    @Override
    public List<Task> getAll() {
        return jpa.findAll().stream()
                .map(TaskEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> getById(Long id) {
        return jpa.findById(id).map(TaskEntity::toModel);
    }

    @Override
    public Page<Task> getAllWithPagination(Pageable pageable) {
        return jpa.findAll(pageable).map(TaskEntity::toModel);
    }

    @Override
    public Task save(Task task) {
        return jpa.save(new TaskEntity(task)).toModel();
    }

    @Override
    public Task update(Task task) {
        return jpa.save(new TaskEntity(task)).toModel();
    }

    @Override
    public void delete(Long id) {
        jpa.findById(id).ifPresent(taskEntity -> {
            taskEntity.setDeletedAt();
            jpa.save(taskEntity);
        });
    }
}
