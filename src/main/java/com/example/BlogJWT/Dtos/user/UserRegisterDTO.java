package com.example.BlogJWT.Dtos.user;

import com.example.BlogJWT.models.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(@NotNull @NotBlank String username, @NotNull @NotBlank String login, @NotNull @NotBlank String password, UserRole role ) {
}
