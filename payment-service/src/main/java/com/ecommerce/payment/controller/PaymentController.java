package com.ecommerce.payment.controller;

import com.ecommerce.payment.dto.PaymentDto;
import com.ecommerce.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Service", description = "Operações de pagamento")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Operation(summary = "Processar pagamento")
    @PostMapping
    public ResponseEntity<PaymentDto.Response> createPayment(@RequestBody PaymentDto.Request request) {
        return ResponseEntity.ok(service.processPayment(request));
    }
}