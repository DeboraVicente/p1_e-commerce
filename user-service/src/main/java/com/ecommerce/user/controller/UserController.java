package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User Service", description = "Operações de usuários")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Criar usuário")
    @PostMapping
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.Request request) {
        return ResponseEntity.ok(service.save(request));
    }

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response> getUserById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}