package com.pt.technical.infrastructure.persistence.entities;

import com.pt.technical.domain.enums.LoanStatusEnum;
import com.pt.technical.domain.enums.converts.LoanStatusEnumConverter;
import com.pt.technical.domain.models.Loan;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;

    private Long identity;

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    private double amount;

    @Convert(converter = LoanStatusEnumConverter.class)
    private LoanStatusEnum status;

    public LoanEntity() {}

    public LoanEntity(Loan load) {
        this.id = load.getId();
        this.customerId = load.getCustomerId();
        this.identity = load.getIdentity();
        this.loanDate = load.getLoanDate();
        this.amount = load.getAmount();
        this.status = load.getStatus();
    }

    public Loan toModel() {
        return new Loan(
            id,
            customerId,
            identity,
            loanDate,
            amount,
            status
        );
    }
}
