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

    public List<Product> listProduct(Number id){
        return repository.getById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Object create(Request request) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}