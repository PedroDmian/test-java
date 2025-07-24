package com.pt.technical.application.usecase;

import com.pt.technical.application.dto.LoanProcessResult;

import java.time.LocalDate;

public interface ProcessLoanPaymentUseCase {
    /**
     * Processes loan payments for a given date.
     *
     * @param currentDate the date for which to process loan payments
     * @param interest the interest rate to apply
     * @param tax the tax rate to apply
     * @param days the number of days to consider for interest calculation
     */
    LoanProcessResult execute(
        LocalDate currentDate,
        double interest,
        double tax,
        Integer days
    );
}
