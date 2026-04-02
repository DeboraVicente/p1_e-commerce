package com.ecommerce.payment.service;

import com.ecommerce.payment.dto.PaymentDto;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final Random random = new Random();

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentDto.Response processPayment(PaymentDto.Request request) {

        String resultStatus = random.nextInt(10) < 8 ? "APROVADO" : "RECUSADO";

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setStatus(resultStatus);

        Payment savedPayment = repository.save(payment);

        PaymentDto.Response response = new PaymentDto.Response();
        response.setId(savedPayment.getId());
        response.setOrderId(savedPayment.getOrderId());
        response.setStatus(savedPayment.getStatus());

        return response;
    }
}