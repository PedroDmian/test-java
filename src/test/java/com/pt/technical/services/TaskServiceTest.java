package com.pt.technical.services;

import com.pt.technical.application.service.TaskService;
import com.pt.technical.domain.models.Task;
import com.pt.technical.domain.repository.TaskRepository;
import org.springframework.data.domain.PageImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testFindTaskById() {
        Task task = new Task(1L, "title", "desc", true, null, null, null, new ArrayList<>());

        when(taskRepository.getById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getById(1L);
        assertNotNull(result);
        assertEquals("title", result.get().getTitle());
    }

    @Test
    void testTaskAll() {
        Task task = new Task(1L, "title", "desc", true, null, null, null, new ArrayList<>());

        when(taskRepository.getAll()).thenReturn(new ArrayList<Task>() {{
            add(task);
        }});

        var result = taskService.getAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("title", result.get(0).getTitle());
    }

    @Test
    void testGetAllWithPagination() {
        Task task = new Task(1L, "title", "desc", true, null, null, null, new ArrayList<>());

        when(taskRepository.getAllWithPagination(null)).thenReturn(new PageImpl<>(new ArrayList<Task>() {{
            add(task);
        }}));

        var result = taskService.getAllWithPagination(null);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("title", result.getContent().get(0).getTitle());
    }

    @Test
    void testSaveTask() {
        Task task = new Task(1L, "title", "desc", true, null, null, null, new ArrayList<>());

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.save(task);
        assertNotNull(result);
        assertEquals("title", result.getTitle());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task(1L, "title", "desc", true, null, null, null, new ArrayList<>());

        when(taskRepository.update(task)).thenReturn(task);

        Task result = taskService.update(task);
        assertNotNull(result);
        assertEquals("title", result.getTitle());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(1L, "title", "desc", true, null, null, LocalDateTime.now(), new ArrayList<>());

        when(taskRepository.getById(1L)).thenReturn(Optional.of(task));
        taskService.delete(1L);

        System.out.println(task.getDeletedAt());

        assertNotNull(task.getDeletedAt());
    }
}
