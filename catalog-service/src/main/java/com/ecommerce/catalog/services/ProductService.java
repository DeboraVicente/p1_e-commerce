package com.ecommerce.catalog.services;

import com.ecommerce.catalog.dto.ProductDto.Request;
import com.ecommerce.catalog.models.Product;
import com.ecommerce.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product listProduct(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Product create(Request request) {
        Product product = new Product();

        product.setDescription(request.getDescription());
        product.setValue(request.getValue());

        return repository.save(product);
    }
}