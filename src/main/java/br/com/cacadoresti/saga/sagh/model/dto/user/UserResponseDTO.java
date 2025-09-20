package br.com.cacadoresti.saga.sagh.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.enums.AppRole;

@Schema(description = "DTO used to represent users in responses")
public record UserResponseDTO(
        UUID id,
        String cpf,
        String name,
        String surname,
        //TODO: Convert date format
        LocalDate birthdate,
        String email,
        Set<AppRole> roles,
        LocalDateTime createdAt
) {}
