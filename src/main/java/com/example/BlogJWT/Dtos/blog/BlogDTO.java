package com.example.BlogJWT.Dtos.blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BlogDTO (@NotBlank @NotNull String texto){

}
