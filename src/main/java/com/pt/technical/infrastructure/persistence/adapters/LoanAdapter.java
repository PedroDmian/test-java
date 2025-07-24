package com.pt.technical.infrastructure.persistence.adapters;

import com.pt.technical.domain.enums.LoanStatusEnum;
import com.pt.technical.domain.models.Loan;
import com.pt.technical.domain.repository.LoanRepository;
import com.pt.technical.infrastructure.persistence.entities.LoanEntity;
import com.pt.technical.infrastructure.persistence.repository.LoanJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanAdapter implements LoanRepository {
    private final LoanJpaRepository jpa;

    @Override
    public List<Loan> getByStatusAndCustomerIdAsc(LoanStatusEnum status, String customerId) {
        return jpa.findByStatusAndCustomerIdOrderByLoanDateAsc(status, customerId).stream()
            .map(LoanEntity::toModel)
            .toList();
    }

    @Override
    public void update(Loan loan) {
        jpa.save(new LoanEntity(loan)).toModel();
    }
}
