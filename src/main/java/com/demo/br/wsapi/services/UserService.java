package com.demo.br.wsapi.services;

import com.demo.br.wsapi.models.entities.User;
import com.demo.br.wsapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(String username) {
        Optional<User> userExistent = getByUsername(username);

        if (userExistent.isPresent()){ // retirar ao implementar login jwt
            return userExistent.get();
        }

        User user = new User(username);
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public Optional<User> getByUsername(String username) {
        return repository.findByNormalizedUsername(username.toLowerCase().trim());
    }
}
