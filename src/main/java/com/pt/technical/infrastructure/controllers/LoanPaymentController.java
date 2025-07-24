package com.pt.technical.infrastructure.controllers;

import com.pt.technical.application.dto.LoanProcessResult;
import com.pt.technical.application.usecase.ProcessLoanPaymentUseCase;
import com.pt.technical.infrastructure.dto.ProcessPaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanPaymentController {
    private final ProcessLoanPaymentUseCase processLoanPaymentUseCase;

    @PostMapping("/process")
    public ResponseEntity<LoanProcessResult> process(
        @Valid @RequestBody ProcessPaymentRequest request
    ) {
        LoanProcessResult response = processLoanPaymentUseCase.execute(
            request.getDateCurrent(),
            request.getInterest(),
            request.getTax(),
            request.getDays()
        );

        return ResponseEntity.ok(response);
    }

}
