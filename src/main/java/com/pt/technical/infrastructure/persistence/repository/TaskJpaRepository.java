package com.pt.technical.infrastructure.persistence.repository;

import com.pt.technical.infrastructure.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
}
