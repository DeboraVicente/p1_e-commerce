package com.ecommerce.payment.dto;

public class PaymentDto {

    public static class Request {
        private Long orderId;
        private String status;

        public Long getOrderId() {
            return orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class Response {
        private Long id;
        private Long orderId;
        private String status;

        public Long getId() {
            return id;
        }

        public Long getOrderId() {
            return orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}