package com.pt.technical.application.dto;

import com.pt.technical.domain.models.DebitAccount;
import com.pt.technical.domain.models.Loan;
import lombok.Getter;

import java.util.List;

public record LoanProcessResult(List<Loan> updatedLoans, List<DebitAccount> updatedAccounts) {
}