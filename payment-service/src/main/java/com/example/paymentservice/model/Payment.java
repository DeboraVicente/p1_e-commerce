package com.example.paymentservice.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderid;
    private String status;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    public Payment() {}

    public Long getId() { return id; }

    public Long getOrderid() { return orderid; }
    public void setOrderid(Long orderid) { this.orderid = orderid; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}