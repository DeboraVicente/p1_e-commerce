package com.ecommerce.payment.controller;

import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.service.PaymentService;

// ===== INÍCIO SWAGGER IMPORTS =====
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
// ===== FIM SWAGGER IMPORTS =====

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
// ===== INÍCIO SWAGGER CLASS =====
@Tag(name = "Payment Service", description = "Operações de pagamento")
// ===== FIM SWAGGER CLASS =====
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // ===== INÍCIO SWAGGER METHOD =====
    @Operation(summary = "Processar pagamento")
    // ===== FIM SWAGGER METHOD =====
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(service.processPayment(payment));
    }
}