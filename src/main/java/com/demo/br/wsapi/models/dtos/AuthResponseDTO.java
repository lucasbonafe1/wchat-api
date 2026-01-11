package com.demo.br.wsapi.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType;
    private LocalDate expiresIn;

    public AuthResponseDTO(
            String accessToken
    ) {
        this.accessToken = accessToken;
        this.expiresIn = LocalDate.now().plusDays(1);
        this.tokenType = "Bearer";
    }
}
