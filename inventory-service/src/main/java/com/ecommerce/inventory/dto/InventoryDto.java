package com.ecommerce.inventory.dto;

public class InventoryDto {

    public static class Request {
        private Integer quantity;
        
        public Integer getQuantity() {
            return quantity;
        }

    }

    public static class Response {
        private Long productid;
        private Integer quantity;

        public Long getProductid() {
            return productid;
        }

        public void setProductid(Object productId) {
            throw new UnsupportedOperationException("Unimplemented method 'setProductid'");
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            throw new UnsupportedOperationException("Unimplemented method 'setQuantity'");
        }


    }

    public static class UpdateResponse {
        private Long productid;
        private String status;
        
        public Long getProductid() {
            return productid;
        }
        
        public void setProductid(Long productId2) {
            throw new UnsupportedOperationException("Unimplemented method 'setProductid'");
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String string) {
            throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
        }
    }
}