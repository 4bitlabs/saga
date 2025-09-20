package br.com.cacadoresti.saga.sagh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cacadoresti.saga.sagh.model.dto.subject.SubjectRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.subject.SubjectResponseDTO;
import br.com.cacadoresti.saga.sagh.service.SubjectService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/subjects")
@Tag(name = "Subjects", description = "Endpoints for subjects management")
@SecurityRequirement(name = "jwtAuthentication")
//TODO: Complete documentation
public class SubjectController {
    private final SubjectService service;
    public SubjectController(SubjectService subjectService) {
        this.service = subjectService;
    }

    //CREATE
    @Operation(summary = "Creates a subject", method = "POST")
    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createSubject(@Valid @RequestBody SubjectRequestDTO subjectCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createGroup(subjectCreateDTO));
    }

    //READ ALL
    @Operation(summary = "Gets all subjects", method = "GET")
    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllSubjects());
    }

    //READ BY ID
    @Operation(summary = "Gets a subject by its ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getSubjectById(id));
    }

    //UPDATE
    @Operation(summary = "Updates a subject by its id", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> updateGroup(@PathVariable UUID id, @Valid @RequestBody SubjectRequestDTO subjectUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateGroup(id, subjectUpdateDTO));
    }

    //DELETE
    @Operation(summary = "Deletes a subject by its ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable UUID id) {
        service.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
