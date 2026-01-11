package com.demo.br.wsapi.models.dtos;

import com.demo.br.wsapi.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    public String username;
    public String normalizedUsername;
    public String email;
    public String password;

    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.normalizedUsername = user.getNormalizedUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
