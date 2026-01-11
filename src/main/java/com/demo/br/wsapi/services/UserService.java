package com.demo.br.wsapi.services;

import com.demo.br.wsapi.models.dtos.LoginDTO;
import com.demo.br.wsapi.models.dtos.RegisterDTO;
import com.demo.br.wsapi.models.entities.User;
import com.demo.br.wsapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }
}
