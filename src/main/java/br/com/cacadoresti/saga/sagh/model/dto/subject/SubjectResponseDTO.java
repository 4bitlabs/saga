package br.com.cacadoresti.saga.sagh.model.dto.subject;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "DTO used to represent groups in responses")
public record SubjectResponseDTO(
        UUID id,
        String code,
        String title,
        String shortTitle,
        LocalDateTime createdAt
) {}
