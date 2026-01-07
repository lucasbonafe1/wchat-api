package com.demo.br.wsapi.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String normalizedUsername;

    public User(String username) {
        this.key = UUID.randomUUID().toString();
        this.username = username;
        this.normalizedUsername = username.toLowerCase().trim();
    }
}
