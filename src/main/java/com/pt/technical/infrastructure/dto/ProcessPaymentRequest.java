package com.pt.technical.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProcessPaymentRequest {

    @NotNull(message = "La fecha actual no puede ser nula")
    private LocalDate dateCurrent;

    @NotNull
    private double interest;

    @NotNull
    private double tax;

    @NotNull
    @Min(1)
    private int days;
}
