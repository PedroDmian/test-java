package com.pt.technical.infrastructure.persistence.repository;

import com.pt.technical.domain.enums.DebitAccountEnum;
import com.pt.technical.infrastructure.persistence.entities.DebitAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebitAccountJpaRepository extends JpaRepository<DebitAccountEntity, Long> {
    List<DebitAccountEntity> getByStatus(DebitAccountEnum status);
}
