package br.com.cacadoresti.saga.sagh.model.dto.subject;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO for creating and updating subjects")
public record SubjectRequestDTO(
        @NotBlank(message = "Invalid subject code")
        String code,

        @NotBlank(message = "Invalid subject title")
        String title,

        @NotBlank(message = "Invalid shortTitle shortTitle")
        String shortTitle
) {}
