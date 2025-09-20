package br.com.cacadoresti.saga.sagh.model.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;


@Schema(description = "DTO for creating and updating students")
public record StudentRequestDTO(
    @NotNull(message = "User ID is obligatory")
    UUID userId,

    @NotNull(message = "The student affiliation starting date is required")
    LocalDate startingDate,

    LocalDate endingDate,

    @NotNull(message = "The student affiliation status is required")
    AffiliationStatus status,

    @NotBlank(message = "Invalid enrollment")
    @Size(message = "The enrollment code must have exactly 10 digits", min = 10, max = 10)
    @Pattern(regexp = "^\\d{10}$", message = "The enrollment code must contain exactly 10 numeric digits")
    String enrollment,

    @Email(message = "Invalid email address")
    String institutionalEmail
) {}
