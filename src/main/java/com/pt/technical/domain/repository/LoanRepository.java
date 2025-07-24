package com.pt.technical.domain.repository;

import com.pt.technical.domain.enums.LoanStatusEnum;
import com.pt.technical.domain.models.Loan;

import java.util.List;

public interface LoanRepository {
    List<Loan> getByStatusAndCustomerIdAsc(LoanStatusEnum status, String customerId);
    void update(Loan loan);
}
