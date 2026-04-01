package com.ecommerce.inventory.dto;

public class InventoryDto {

    public static class Request {
        private Integer quantity;
        
        public Integer getQuantity() {
            return quantity;
        }

    }

    public static class Response {
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

    public static class UpdateResponse {
        private Long productId;
        private String status;
        
        public Long getProductId() {
            return productId;
        }
        
        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}