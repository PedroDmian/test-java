package com.pt.technical.infrastructure.persistence.entities;

import com.pt.technical.domain.enums.DebitAccountEnum;
import com.pt.technical.domain.enums.converts.DebitAccountEnumConverter;
import com.pt.technical.domain.models.DebitAccount;
import jakarta.persistence.*;

@Entity
@Table(name = "debit_accounts")
public class DebitAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;

    private double balance;

    @Convert(converter = DebitAccountEnumConverter.class)
    private DebitAccountEnum status;

    public DebitAccountEntity() {}

    public DebitAccountEntity(DebitAccount debitAccount) {
        this.id = debitAccount.getId();
        this.customerId = debitAccount.getCustomerId();
        this.balance = debitAccount.getBalance();
        this.status = debitAccount.getStatus();
    }

    public DebitAccount toModel() {
        return new DebitAccount(
            id,
            customerId,
            balance,
            status
        );
    }
}
