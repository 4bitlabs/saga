package br.com.cacadoresti.saga.sagh.model.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.dto.professor.ProfessorResponseDTO;
import br.com.cacadoresti.saga.sagh.model.dto.student.StudentResponseDTO;
import br.com.cacadoresti.saga.sagh.model.enums.GroupStatus;

@Schema(description = "DTO used to represent groups in responses")
public record GroupResponseDTO(
        UUID id,
        String code,
        UUID subjectId,
        String academicPeriod,
        GroupStatus status,
        Set<ProfessorResponseDTO> professors,
        Set<StudentResponseDTO> students,
        LocalDateTime createdAt
) {}
