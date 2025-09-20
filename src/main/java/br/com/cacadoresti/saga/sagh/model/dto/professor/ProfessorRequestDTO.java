package br.com.cacadoresti.saga.sagh.model.dto.professor;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;
import br.com.cacadoresti.saga.sagh.model.enums.EducationLevel;

@Schema(description = "DTO used to represent professors in responses")
public record ProfessorRequestDTO(
        @NotNull(message = "User ID is obligatory")
        UUID userId,

        @NotNull(message = "Staring date is obligatory")
        LocalDate startingDate,

        LocalDate endingDate,

        @NotNull(message = "Invalid status")
        AffiliationStatus status,

        @NotBlank(message = "Siape field is obligatory")
        @Pattern(message = "SIAPE code must contain exactly 7 numeric digits", regexp = "^\\d{7}$")
        String siape,

        @NotNull(message = "Invalid education level")
        EducationLevel education,

//        @NotNull(message = "Department ID is obligatory")
//        UUID departmentId,

        @Email(message = "Invalid email address")
        //TODO: Implement RegExr
        String institutionalEmail
) {}
