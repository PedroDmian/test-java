package com.pt.technical.infrastructure.persistence.adapters;

import com.pt.technical.domain.enums.DebitAccountEnum;
import com.pt.technical.domain.models.DebitAccount;
import com.pt.technical.domain.repository.DebitAccountRepository;
import com.pt.technical.infrastructure.persistence.entities.DebitAccountEntity;
import com.pt.technical.infrastructure.persistence.repository.DebitAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DebitAccountAdapter implements DebitAccountRepository {
    public final DebitAccountJpaRepository jpa;

    @Override
    public List<DebitAccount> getByStatus(DebitAccountEnum status) {
        return jpa.getByStatus(status).stream()
            .map(DebitAccountEntity::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<DebitAccount> getAll() {
        return jpa.findAll().stream()
            .map(DebitAccountEntity::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public void update(DebitAccount account) {
        jpa.save(new DebitAccountEntity(account)).toModel();
    }
}
