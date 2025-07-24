package com.pt.technical.domain.models;

import com.pt.technical.domain.enums.DebitAccountEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DebitAccount {
    private Long id;

    private String customerId;

    private double balance;

    private DebitAccountEnum status;
}
