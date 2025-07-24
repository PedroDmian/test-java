package com.pt.technical.exceptions;

import com.pt.technical.infrastructure.persistence.entities.TaskEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestEceptionHandlerTest {
    static class TestableTaskEntity extends TaskEntity {
        @Override
        public void onCreate() {
            super.onCreate();
        }
        @Override
        public void onUpdate() {
            super.onUpdate();
        }
    }

    @Test
    void onCreateAtAndUpdatedAt() {
        TestableTaskEntity entity = new TestableTaskEntity();
        entity.onCreate();

        assertNotNull(entity.toModel().getCreatedAt());
        assertNotNull(entity.toModel().getUpdatedAt());
    }

    @Test
    void onUpdateAt() throws InterruptedException {
        TestableTaskEntity entity = new TestableTaskEntity();
        entity.onCreate();
        LocalDateTime before = entity.toModel().getUpdatedAt();

        Thread.sleep(5);

        entity.onUpdate();
        LocalDateTime after = entity.toModel().getUpdatedAt();

        assertTrue(after.isAfter(before));
    }
}
