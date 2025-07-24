package com.pt.technical.application.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import com.pt.technical.domain.models.Task;

public interface GetTaskUseCase {
    Optional<Task> getById(Long id);
    List<Task> getAll();
    Page<Task> getAllWithPagination(Pageable pageable);
}
