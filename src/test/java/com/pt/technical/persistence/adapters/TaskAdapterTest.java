package com.pt.technical.persistence.adapters;

import com.pt.technical.domain.models.Task;
import com.pt.technical.infrastructure.persistence.adapters.TaskAdapter;
import com.pt.technical.infrastructure.persistence.entities.TaskEntity;
import com.pt.technical.infrastructure.persistence.repository.TaskJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskAdapterTest {
    @Mock
    private TaskJpaRepository jpa;

    @InjectMocks
    private TaskAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReturnsListOfTasks() {
        List<TaskEntity> entities = List.of(mock(TaskEntity.class));
        when(jpa.findAll()).thenReturn(entities);
        when(entities.get(0).toModel()).thenReturn(mock(Task.class));

        List<Task> result = adapter.getAll();

        assertEquals(1, result.size());
        verify(jpa).findAll();
    }

    @Test
    void getByIdReturnsTaskIfExists() {
        TaskEntity entity = mock(TaskEntity.class);
        when(jpa.findById(1L)).thenReturn(Optional.of(entity));
        when(entity.toModel()).thenReturn(mock(Task.class));

        Optional<Task> result = adapter.getById(1L);

        assertTrue(result.isPresent());
        verify(jpa).findById(1L);
    }

    @Test
    void getAllWithPaginationReturnsPageOfTasks() {
        Pageable pageable = PageRequest.of(0, 10);
        TaskEntity entity = mock(TaskEntity.class);
        Page<TaskEntity> page = new PageImpl<>(List.of(entity));
        when(jpa.findAll(pageable)).thenReturn(page);
        when(entity.toModel()).thenReturn(mock(Task.class));

        Page<Task> result = adapter.getAllWithPagination(pageable);

        assertEquals(1, result.getTotalElements());
        verify(jpa).findAll(pageable);
    }

    @Test
    void savePersistsAndReturnsTask() {
        Task task = mock(Task.class);
        TaskEntity entity = mock(TaskEntity.class);
        when(jpa.save(any(TaskEntity.class))).thenReturn(entity);
        when(entity.toModel()).thenReturn(task);

        Task result = adapter.save(task);

        assertEquals(task, result);
        verify(jpa).save(any(TaskEntity.class));
    }

    @Test
    void updatePersistsAndReturnsTask() {
        Task task = mock(Task.class);
        TaskEntity entity = mock(TaskEntity.class);
        when(jpa.save(any(TaskEntity.class))).thenReturn(entity);
        when(entity.toModel()).thenReturn(task);

        Task result = adapter.update(task);

        assertEquals(task, result);
        verify(jpa).save(any(TaskEntity.class));
    }

    @Test
    void deleteSetsDeletedAtIfExists() {
        TaskEntity entity = mock(TaskEntity.class);
        when(jpa.findById(1L)).thenReturn(Optional.of(entity));

        adapter.delete(1L);

        verify(entity).setDeletedAt();
        verify(jpa).save(entity);
    }
}
