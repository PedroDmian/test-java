package com.pt.technical.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import com.pt.technical.domain.models.Task;


public interface TaskRepository {
    Optional<Task> getById(Long id);
    List<Task> getAll();
    Page<Task> getAllWithPagination(Pageable pageable);
    Task save(Task task);
    Task update(Task task);
    void delete(Long id);
}
