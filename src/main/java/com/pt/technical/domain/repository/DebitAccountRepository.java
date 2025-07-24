package com.pt.technical.domain.repository;

import com.pt.technical.domain.enums.DebitAccountEnum;
import com.pt.technical.domain.models.DebitAccount;

import java.util.List;

public interface DebitAccountRepository {
    List<DebitAccount> getByStatus(DebitAccountEnum status);
    List<DebitAccount> getAll();
    void update(DebitAccount account);
}
