package com.ecommerce.user.service;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto.Response save(UserDto.Request request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User savedUser = repository.save(user);

        UserDto.Response response = new UserDto.Response();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setStatus("SUCCESS");

        return response;
    }

    public Optional<UserDto.Response> findById(Long id) {
        return repository.findById(id)
                .map(user -> {
                    UserDto.Response response = new UserDto.Response();
                    response.setId(user.getId());
                    response.setName(user.getName());
                    response.setEmail(user.getEmail());
                    response.setStatus("SUCCESS");
                    return response;
                });
    }
}