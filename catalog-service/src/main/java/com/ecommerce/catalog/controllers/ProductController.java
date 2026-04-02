package com.ecommerce.catalog.controllers;

import com.ecommerce.catalog.dto.ProductDto;
import com.ecommerce.catalog.dto.ProductDto.ResponseCreate;
import com.ecommerce.catalog.models.Product;
import com.ecommerce.catalog.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
@Tag(name = "Catalog Service", description = "Gerenciamento de produtos")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca produto por ID")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.listProduct(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    @Operation(summary = "Cadastra novo produto")
    public ResponseCreate create(@RequestBody ProductDto.Request request) {

        var product = ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); 

        return new ResponseCreate(product.getBody().getId(), "Produto criado com sucesso");
    }
}