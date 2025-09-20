package br.com.cacadoresti.saga.sagh.model.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.interfaces.ConcreteAffiliationResponseDTO;
import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;

@Schema(description = "DTO used to represent students in responses")
public record StudentResponseDTO(
    UUID userId,
    String name,
    AffiliationStatus status,
    String enrollment,
    String institutionalEmail,
    LocalDateTime createdAt
) implements ConcreteAffiliationResponseDTO {}
