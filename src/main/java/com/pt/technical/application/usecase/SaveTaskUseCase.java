package com.pt.technical.application.usecase;

import com.pt.technical.domain.models.Task;

public interface SaveTaskUseCase {
    Task save(Task task);
}
