package com.ecommerce.inventory.services;

import com.ecommerce.inventory.dto.InventoryDto;
import com.ecommerce.inventory.models.Inventory;
import com.ecommerce.inventory.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public InventoryDto.Response findByProductId(Long productId) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));

        InventoryDto.Response r = new InventoryDto.Response();
        r.setProductId(inv.getProductId());
        r.setQuantity(inv.getQuantity());
        return r;
    }

    public InventoryDto.UpdateResponse updateQuantity(Long productId, InventoryDto.Request request) {
        Inventory inv = repository.findByProductId(productId).orElseGet(() -> {
            Inventory novo = new Inventory();
            novo.setProductId(productId);
            novo.setQuantity(0);
            return novo;
        });

        if (request.getQuantity() < 0) {
            throw new RuntimeException("A quantidade não pode ser negativa");
        }

        inv.setQuantity(request.getQuantity());
        repository.save(inv);

        InventoryDto.UpdateResponse r = new InventoryDto.UpdateResponse();
        r.setProductId(productId);
        r.setStatus("ATUALIZADO");
        return r;
    }

    public void decrementStock(Long productId, Integer quantity) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque: " + productId));

        if (inv.getQuantity() < quantity) {
            throw new RuntimeException("Estoque insuficiente para o produto " + productId
                    + ". Disponível: " + inv.getQuantity() + ", solicitado: " + quantity);
        }

        inv.setQuantity(inv.getQuantity() - quantity);
        repository.save(inv);
    }

    public boolean hasStock(Long productId, Integer quantity) {
        return repository.findByProductId(productId)
                .map(inv -> inv.getQuantity() >= quantity)
                .orElse(false);
    }
}