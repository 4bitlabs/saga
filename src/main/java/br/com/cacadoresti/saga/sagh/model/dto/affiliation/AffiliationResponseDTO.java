package br.com.cacadoresti.saga.sagh.model.dto.affiliation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.interfaces.ConcreteAffiliationResponseDTO;
import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;

@Schema(description = "DTO used to represent affiliations in responses")
public record AffiliationResponseDTO(
        UUID id,
        UUID userId,
        LocalDate startingDate,
        LocalDate endingDate,
        String affiliationType,
        AffiliationStatus status,
        ConcreteAffiliationResponseDTO affiliationDetails,
        LocalDateTime createdAt
) {}
