package com.pt.technical.domain.enums;

public enum LoanStatusEnum {
    PENDING("Pendiente"),
    PAID("Pagado");

    private final String status;

    LoanStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
