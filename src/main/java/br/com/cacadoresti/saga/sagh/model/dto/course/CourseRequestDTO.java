package br.com.cacadoresti.saga.sagh.model.dto.course;

import br.com.cacadoresti.saga.sagh.model.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO for creating and updating courses")
public record CourseRequestDTO(
    @NotBlank(message = "Invalid course code")
    String code,

    @NotBlank(message = "Invalid course title")
    String title,

    @NotBlank(message = "Invalid course shortName")
    String shortTitle,

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Invalid course type")
    CourseType type,

    @Min(value = 1, message = "A course must have at least one semester")
    Integer quantityOfSemesters
) {}
