package com.ecommerce.inventory.controllers;

import com.ecommerce.inventory.dto.InventoryDto;
import com.ecommerce.inventory.services.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
@Tag(name = "Inventory Service", description = "Controle de estoque de produtos")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{productId}")
    @Operation(summary = "Consulta estoque de um produto")
    public ResponseEntity<?> getStock(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(service.findByProductId(productId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Atualiza quantidade em estoque")
    public ResponseEntity<?> updateStock(@PathVariable Long productId,
                                         @RequestBody InventoryDto.Request request) {
        try {
            return ResponseEntity.ok(service.updateQuantity(productId, request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{productId}/decrement")
    @Operation(summary = "Decrementa estoque (chamado internamente pelo Order Service)")
    public ResponseEntity<?> decrementStock(@PathVariable Long productId,
                                             @RequestParam Integer quantity) {
        try {
            service.decrementStock(productId, quantity);
            return ResponseEntity.ok(Map.of("status", "DECREMENTADO"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
}