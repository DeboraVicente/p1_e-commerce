package com.ecommerce.orders.dto;

import java.util.List;

public class OrderDto {

    public static class ItemRequest {
        private Long productid;
        private Integer quantity;

        public Long getProductid() {
            return productid;
        }
        public Integer getQuantity() {
            return quantity;
        }
    }

    public static class Request {
        private Long userid;
        private List<ItemRequest> items;
    
        public Long getuserid() {
            return userid;
        }
        public List<ItemRequest> getItems() {
            return items;
        }
        public Object getUserid() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getUserid'");
        }
    }

    public static class Response {
        private Long id;
        private String status;
        private Double paymentAmount;
    
    
        public Long getid() {
            return id;
        }
        public String getStatus() {
            return status;
        }
        public Double getPaymentAmount() {
            return paymentAmount;
        }
        public void setId(Object id2) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setId'");
        }
        public void setStatus(Object status2) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
        }
        public void setPaymentAmount(Object paymentAmount2) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setPaymentAmount'");
        }
    }

    public static class ProductResponse {
        private Long id;
        private String description;
        private Double value;

        public Long getid() {
            return id;
        }
        public String getDescription() {
            return description;
        }
        public Double getValue() {
            return value;
        }
    }

    public static class InventoryResponse {
        private Long productid;
        private Integer quantity;

        public Long getProductid() {
            return productid;
        }
        public Integer getQuantity() {
            return quantity;
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
        public void setOrderId(Object id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setOrderId'");
        }
        public void setStatus(String string) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
        }
    }

    public static class PaymentResponse {
        private Long id;
        private Long orderId;
        private String status;
        
        public Long getid() {
            return id;
        }
        public Long getOrderId() {
            return orderId;
        }
        public String getStatus() {
            return status;
        }
    }
}