package com.pt.technical.infrastructure.controllers;

import com.pt.technical.domain.models.Task;
import com.pt.technical.application.usecase.DeleteTaskUseCase;
import com.pt.technical.application.usecase.GetTaskUseCase;
import com.pt.technical.application.usecase.UpdateTaskUseCase;
import com.pt.technical.application.usecase.SaveTaskUseCase;
import com.pt.technical.infrastructure.dto.TaskRequest;
import com.pt.technical.infrastructure.mappers.TaskMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final SaveTaskUseCase saveTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<Page<Task>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(getTaskUseCase.getAllWithPagination(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return getTaskUseCase.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> save(
        @Valid @RequestBody TaskRequest request
    ) {
        Task task = taskMapper.toModel(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveTaskUseCase.save(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(updateTaskUseCase.update(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteTaskUseCase.delete(id);

        return ResponseEntity.noContent().build();
    }
}
