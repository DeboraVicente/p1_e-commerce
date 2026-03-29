package com.ecommerce.inventory.models;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long productId;

    private Integer quantity;

    public Integer getQuantity() {
        throw new UnsupportedOperationException("Unimplemented method 'getQuantity'");
    }

    public void setQuantity(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'setQuantity'");
    }

    public void setProductId(Long productId2) {
        throw new UnsupportedOperationException("Unimplemented method 'setProductId'");
    }

    public Object getProductId() {
        throw new UnsupportedOperationException("Unimplemented method 'getProductId'");
    }
}