package com.pt.technical.controllers;

import com.pt.technical.application.usecase.DeleteTaskUseCase;
import com.pt.technical.application.usecase.GetTaskUseCase;
import com.pt.technical.application.usecase.SaveTaskUseCase;
import com.pt.technical.application.usecase.UpdateTaskUseCase;
import com.pt.technical.domain.models.Task;
import com.pt.technical.domain.models.TaskLine;
import com.pt.technical.infrastructure.controllers.TaskController;
import com.pt.technical.infrastructure.dto.TaskRequest;
import com.pt.technical.infrastructure.mappers.TaskMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private SaveTaskUseCase saveTaskUseCase;

    @Mock
    private UpdateTaskUseCase updateTaskUseCase;

    @Mock
    private DeleteTaskUseCase deleteTaskUseCase;

    @Mock
    private GetTaskUseCase getTaskUseCase;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void testGetAll() {
        Pageable pageable = PageRequest.of(0, 10);

        TaskLine line = new TaskLine(null, "Test Line", "This is a test line");
        TaskLine line2 = new TaskLine(null, "Test Line 1", "This is a test line 1");

        List<Task> tasks = List.of(new Task(
            1L, "Test Task", "This is a test task", false, null, null, null, new ArrayList<>(List.of(line, line2))
        ));
        Page<Task> page = new PageImpl<>(tasks, pageable, tasks.size());

        when(getTaskUseCase.getAllWithPagination(pageable)).thenReturn(page);

        ResponseEntity<Page<Task>> response = taskController.getAll(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    void testGetByIdFound() {
        Task task = new Task(1L, "Test Task", "This is a test task", false, null, null, null, new ArrayList<>());
        task.setId(1L);

        when(getTaskUseCase.getById(1L)).thenReturn(Optional.of(task));

        ResponseEntity<Task> response = taskController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetByIdNotFound() {
        when(getTaskUseCase.getById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskController.getById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testSaveTask() {
        TaskRequest request = new TaskRequest();
        Task task = new Task(1L, "Test Task", "This is a test task", false, null, null, null, new ArrayList<>());
        Task savedTask = new Task(1L, "Test Task", "This is a test task", false, null, null, null, new ArrayList<>());
        savedTask.setId(1L);

        when(taskMapper.toModel(request)).thenReturn(task);
        when(saveTaskUseCase.save(task)).thenReturn(savedTask);

        ResponseEntity<Task> response = taskController.save(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task(1L, "Test Task", "This is a test task", false, null, null, null, new ArrayList<>());
        task.setId(1L);

        when(updateTaskUseCase.update(task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.update(1L, task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testDeleteTask() {
        doNothing().when(deleteTaskUseCase).delete(1L);

        ResponseEntity<Void> response = taskController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
