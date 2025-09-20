package br.com.cacadoresti.saga.sagh.model.dto.administrative_public_servant;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

import br.com.cacadoresti.saga.sagh.interfaces.ConcreteAffiliationResponseDTO;

@Schema(description = "DTO used to represent administrative public servants in responses")
public record AdministrativePublicServantResponseDTO(
        UUID userId,
        String name
) implements ConcreteAffiliationResponseDTO {}
