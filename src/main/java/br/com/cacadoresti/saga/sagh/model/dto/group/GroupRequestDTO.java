package br.com.cacadoresti.saga.sagh.model.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.enums.GroupStatus;
import br.com.cacadoresti.saga.sagh.model.enums.YearSemester;

@Schema(description = "DTO for creating and updating groups")
public record GroupRequestDTO(
        @NotBlank(message = "Invalid group code")
        String code,

        @NotNull(message = "Subject ID is required")
        UUID subjectId,

        @NotNull(message = "Year is required")
        Integer year,

        @NotNull(message = "Semester is required")
        YearSemester semester,

        @NotNull(message = "Group status is required")
        GroupStatus status
) {}
