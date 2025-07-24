package com.pt.technical.persistence.entities;

import com.pt.technical.domain.models.Task;
import com.pt.technical.domain.models.TaskLine;
import com.pt.technical.infrastructure.persistence.entities.TaskEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskEntityTest {

    @Test
    void constructorFromTaskSetsFieldsCorrectly() {
        TaskLine line1 = new TaskLine(
            1L,
            "Task Line 1",
            "Description one"
        );
        TaskLine line2 = new TaskLine(
            2L,
            "Task Line 2",
            "Description two"
        );

        Task task = new Task(
            1L,
            "Título",
            "Descripción",
            true,
            LocalDateTime.now(),
            LocalDateTime.now(),
            null,
            List.of(line1, line2)
        );

        TaskEntity entity = new TaskEntity(task);

        assertEquals(task.getId(), entity.toModel().getId());
        assertEquals(task.getTitle(), entity.toModel().getTitle());
        assertEquals(task.getDescription(), entity.toModel().getDescription());
        assertEquals(task.isCompleted(), entity.toModel().isCompleted());
    }

    @Test
    void setDeletedAtSetsDeletedAtField() {
        TaskEntity entity = new TaskEntity();
        assertNull(entity.toModel().getDeletedAt());

        entity.setDeletedAt();

        assertNotNull(entity.toModel().getDeletedAt());
    }

    @Test
    void onCreateSetsCreatedAtAndUpdatedAt() {
        TaskEntity entity = new TaskEntity();
        entity.onCreate();

        assertNotNull(entity.toModel().getCreatedAt());
        assertNotNull(entity.toModel().getUpdatedAt());
    }

    @Test
    void onUpdateSetsUpdatedAt() {
        TaskEntity entity = new TaskEntity();
        entity.onCreate();
        LocalDateTime beforeUpdate = entity.toModel().getUpdatedAt();

        try { Thread.sleep(10); } catch (InterruptedException ignored) {}

        entity.onUpdate();
        LocalDateTime afterUpdate = entity.toModel().getUpdatedAt();

        assertTrue(afterUpdate.isAfter(beforeUpdate));
    }
}
