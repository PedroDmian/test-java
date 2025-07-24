package com.pt.technical.domain.models;

import com.pt.technical.domain.enums.LoanStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class Loan {
    private Long id;

    private String customerId;

    private Long identity;

    private LocalDate loanDate;

    private double amount;

    private LoanStatusEnum status;
}
