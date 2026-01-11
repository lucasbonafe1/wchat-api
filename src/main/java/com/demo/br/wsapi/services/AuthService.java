package com.demo.br.wsapi.services;

import com.demo.br.wsapi.models.dtos.AuthResponseDTO;
import com.demo.br.wsapi.models.dtos.LoginDTO;
import com.demo.br.wsapi.models.dtos.RegisterDTO;
import com.demo.br.wsapi.models.dtos.UserResponseDTO;
import com.demo.br.wsapi.models.entities.User;
import com.demo.br.wsapi.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    public UserResponseDTO register(RegisterDTO body) throws Exception {
        Optional<User> userExistent = repository.findByEmail(body.getEmail());
        if (userExistent.isPresent()) {
            throw new Exception("Já existe um usuário com esse e-mail.");
        }

        User user = new User(
                body.getUsername(),
                body.getNormalizedUsername(),
                body.getEmail(),
                passwordEncoder.encode(body.getPassword())
        );

        repository.save(user);
        return new UserResponseDTO(user);
    }

    public AuthResponseDTO login(LoginDTO body) throws Exception {
        Optional<User> potentialUser = repository.findByEmail(body.getEmail());

        if (potentialUser.isEmpty()) {
            throw new UserPrincipalNotFoundException("Email inválido.");
        }

        User user = potentialUser.get();

        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new Exception("Senha incorreta.");
        }

        String token = jwtTokenService.generateToken(user);

        return new AuthResponseDTO(token);
    }
}

