package com.ecommerce.catalog.models;
 
import jakarta.persistence.*;
 
@Entity
@Table(name = "catalog")
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double value;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
}