package br.com.cacadoresti.saga.sagh.model.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO used to represent departments in responses")
public record DepartmentResponseDTO(
    String code,
    String name
) {}
