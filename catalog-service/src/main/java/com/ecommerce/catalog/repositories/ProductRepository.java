package com.ecommerce.catalog.repositories;

import com.ecommerce.catalog.models.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getById(Number id);
}