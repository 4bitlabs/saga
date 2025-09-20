package br.com.cacadoresti.saga.sagh.model.dto.professor;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.interfaces.ConcreteAffiliationResponseDTO;
import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;
import br.com.cacadoresti.saga.sagh.model.enums.EducationLevel;

@Schema(description = "DTO used to represent professors in responses")
public record ProfessorResponseDTO(
    UUID userId,
    String siape,
    String name,
    String surname,
    AffiliationStatus status,
    EducationLevel education,
    String institutionalEmail,
    LocalDateTime createdAt
) implements ConcreteAffiliationResponseDTO {}
