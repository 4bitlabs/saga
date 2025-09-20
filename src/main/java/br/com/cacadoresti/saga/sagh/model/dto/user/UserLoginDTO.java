package br.com.cacadoresti.saga.sagh.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for logging in users")
public record UserLoginDTO(
        String username,
        String password
) {}
