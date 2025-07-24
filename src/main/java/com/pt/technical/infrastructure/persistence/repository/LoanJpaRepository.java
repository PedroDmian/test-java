package com.pt.technical.infrastructure.persistence.repository;

import com.pt.technical.domain.enums.LoanStatusEnum;
import com.pt.technical.infrastructure.persistence.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanJpaRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByStatusAndCustomerIdOrderByLoanDateAsc(LoanStatusEnum status, String customerId);
}
