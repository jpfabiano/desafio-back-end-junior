package com.example.BlogJWT.Dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginDTO (@NotNull @NotBlank String login, @NotNull @NotBlank String password){
}
