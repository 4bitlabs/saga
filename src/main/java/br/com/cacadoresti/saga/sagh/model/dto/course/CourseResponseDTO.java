package br.com.cacadoresti.saga.sagh.model.dto.course;

import br.com.cacadoresti.saga.sagh.model.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO used to represent courses in responses")
public record CourseResponseDTO(
        String code,
        String title,
        String shortTitle,
        CourseType type,
        Integer quantityOfSemesters
) {}
