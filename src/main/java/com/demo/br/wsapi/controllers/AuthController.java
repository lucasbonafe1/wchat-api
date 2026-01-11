package com.demo.br.wsapi.controllers;

import com.demo.br.wsapi.models.dtos.AuthResponseDTO;
import com.demo.br.wsapi.models.dtos.LoginDTO;
import com.demo.br.wsapi.models.dtos.RegisterDTO;
import com.demo.br.wsapi.models.dtos.UserResponseDTO;
import com.demo.br.wsapi.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterDTO body) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(body));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO body) throws Exception {
        return ResponseEntity.ok(authService.login(body));
    }
}
