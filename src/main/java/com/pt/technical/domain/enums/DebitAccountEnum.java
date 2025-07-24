package com.pt.technical.domain.enums;

public enum DebitAccountEnum {
    ACTIVE("Activa"),
    BLOCKED("Bloqueada"),
    CANCELLED("Cancelada");

    private final String status;

    DebitAccountEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
