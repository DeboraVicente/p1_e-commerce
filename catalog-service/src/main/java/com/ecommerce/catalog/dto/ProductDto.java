package com.ecommerce.catalog.dto;

import lombok.Data;

public class ProductDto {

    @Data
    public static class Request {
        private String description;
        private Double value;
    }

    @Data
    public static class Response {
        private Long id;
        private String description;
        private Double value;
    }

    @Data
    public static class CreateResponse {
        private Long id;
        private String message;
    }
}