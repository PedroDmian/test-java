package com.pt.technical.application.service;

import com.pt.technical.application.usecase.DeleteTaskUseCase;
import com.pt.technical.application.usecase.GetTaskUseCase;
import com.pt.technical.application.usecase.SaveTaskUseCase;
import com.pt.technical.application.usecase.UpdateTaskUseCase;

import com.pt.technical.domain.repository.TaskRepository;
import com.pt.technical.domain.models.Task;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskService implements SaveTaskUseCase, UpdateTaskUseCase, DeleteTaskUseCase, GetTaskUseCase {
    private final TaskRepository taskRepository;

    @Override
    public Page<Task> getAllWithPagination(Pageable pageable) {
        return taskRepository.getAllWithPagination(pageable);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public Optional<Task> getById(Long taskId) {
        return taskRepository.getById(taskId);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.update(task);
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.delete(taskId);
    }
}
