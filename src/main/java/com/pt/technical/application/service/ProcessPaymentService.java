package com.pt.technical.application.service;

import com.pt.technical.application.dto.LoanProcessResult;
import com.pt.technical.application.usecase.ProcessLoanPaymentUseCase;
import com.pt.technical.domain.enums.DebitAccountEnum;
import com.pt.technical.domain.enums.LoanStatusEnum;
import com.pt.technical.domain.models.DebitAccount;
import com.pt.technical.domain.models.Loan;
import com.pt.technical.domain.repository.DebitAccountRepository;
import com.pt.technical.domain.repository.LoanRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProcessPaymentService implements ProcessLoanPaymentUseCase {
    private final LoanRepository loanRepository;
    private final DebitAccountRepository accountRepository;

    @Override
    public LoanProcessResult execute(LocalDate currentDate, double interest, double tax, Integer days) {
        List<Loan> updatedLoans = new ArrayList<>();
        List<DebitAccount> updatedAccounts = new ArrayList<>();

        List<DebitAccount> accounts = accountRepository.getByStatus(DebitAccountEnum.ACTIVE);

        double finalInterest = interest / 100;
        double finalTax = tax / 100;

        for (DebitAccount account : accounts) {
            List<Loan> loans = loanRepository.getByStatusAndCustomerIdAsc(LoanStatusEnum.PENDING, account.getCustomerId());

            loans.forEach(loan -> {
                long daysLine = ChronoUnit.DAYS.between(loan.getLoanDate(), currentDate);

                BigDecimal interestAmount = BigDecimal.valueOf(loan.getAmount())
                        .multiply(BigDecimal.valueOf(daysLine))
                        .multiply(BigDecimal.valueOf(finalInterest))
                        .divide(BigDecimal.valueOf(days), RoundingMode.HALF_UP);

                BigDecimal iva = interestAmount.multiply(BigDecimal.valueOf(finalTax));
                BigDecimal total = BigDecimal.valueOf(loan.getAmount()).add(interestAmount).add(iva);

                BigDecimal accountBalance = BigDecimal.valueOf(account.getBalance());

                if (accountBalance.compareTo(total) >= 0) {
                    account.setBalance(accountBalance.subtract(total).doubleValue());
                    loan.setStatus(LoanStatusEnum.PAID);
                    loanRepository.update(loan);
                    accountRepository.update(account);

                    updatedLoans.add(loan);
                }
            });
        }

        return new LoanProcessResult(updatedLoans, accountRepository.getAll());
    }
}
