package com.example.BlogJWT.Dtos.blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BlogUpdateDto(@NotBlank @NotNull String texto ) {
    public String getTexto (){
        return texto;
    }

}
