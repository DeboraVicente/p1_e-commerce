package com.ecommerce.catalog.model;
 
import jakarta.persistence.*;
import lombok.Data;
 
@Data
@Entity
@Table(name = "catalog")
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String description;
 
    private Double value;
}