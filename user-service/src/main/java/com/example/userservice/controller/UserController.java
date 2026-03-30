package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

// ===== INÍCIO SWAGGER IMPORTS =====
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
// ===== FIM SWAGGER IMPORTS =====

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
// ===== INÍCIO SWAGGER CLASS =====
@Tag(name = "User Service", description = "Operações de usuários")
// ===== FIM SWAGGER CLASS =====
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ===== INÍCIO SWAGGER METHOD =====
    @Operation(summary = "Criar usuário")
    // ===== FIM SWAGGER METHOD =====
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        return ResponseEntity.ok(Map.of(
                "id", savedUser.getId(),
                "status", "SUCCESS"
        ));
    }

    // ===== INÍCIO SWAGGER METHOD =====
    @Operation(summary = "Buscar usuário por ID")
    // ===== FIM SWAGGER METHOD =====
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return service.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}