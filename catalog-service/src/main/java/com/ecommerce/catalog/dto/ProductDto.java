package com.ecommerce.catalog.dto;

public class ProductDto {

    public static class Request {
        private String description;
        private Double value;
        
        public String getDescription() {
            return description;
        }

        public Double getValue() {
            return value;
        }
    }

    public static class Response {
        private Long id;
        private String description;
        private Double value;

        public Long getId(){
            return id;
        }

        public String getDescription() {
            return description;
        }

        public Double getValue() {
            return value;
        }
    }

}