package com.ecommerce.order.dto;

import java.util.List;

public class OrderDTO {

    public static class ItemRequest {
        private Long productId;
        private Integer quantity;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public static class Request {
        private Long userId;
        private List<ItemRequest> items;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public List<ItemRequest> getItems() {
            return items;
        }

        public void setItems(List<ItemRequest> items) {
            this.items = items;
        }
    }

    public static class Response {
        private Long id;
        private String status;
        private Double paymentAmount;

        public Long getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }

        public Double getPaymentAmount() {
            return paymentAmount;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setPaymentAmount(Double paymentAmount) {
            this.paymentAmount = paymentAmount;
        }
    }

    public static class ProductResponse {
        private Long id;
        private String description;
        private Double value;

        public Long getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public Double getValue() {
            return value;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setValue(Double value) {
            this.value = value;
        }
    }

    public static class InventoryResponse {
        private Long productId;
        private Integer quantity;

        public Long getProductId() {
            return productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public static class PaymentRequest {
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

    public static class PaymentResponse {
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