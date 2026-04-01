package com.ecommerce.payment.service;

import com.ecommerce.payment.dto.PaymentDto;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentDto.Response processPayment(PaymentDto.Request request) {

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());

        // Serviço decide o status
        payment.setStatus("APROVADO");

        Payment savedPayment = repository.save(payment);

        PaymentDto.Response response = new PaymentDto.Response();
        response.setId(savedPayment.getId());
        response.setOrderId(savedPayment.getOrderId());
        response.setStatus(savedPayment.getStatus());

        return response;
    }
}