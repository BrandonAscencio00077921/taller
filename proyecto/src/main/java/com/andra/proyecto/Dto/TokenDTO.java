package com.andra.proyecto.Dto;

import com.andra.proyecto.Entities.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {
    private String token;

    public TokenDTO(Token token) {
        this.token = token.getContent();
    }
}
