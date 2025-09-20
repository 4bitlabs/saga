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

import br.com.cacadoresti.saga.sagh.model.dto.department.DepartmentRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.department.DepartmentResponseDTO;
import br.com.cacadoresti.saga.sagh.service.DepartmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/departments")
@Tag(name = "Departments", description = "Endpoints for departments management")
@SecurityRequirement(name = "jwtAuthentication")
//TODO: Complete documentation
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService departmentService) {
        this.service = departmentService;
    }

    //CREATE
    @Operation(summary = "Creates a department", method = "POST")
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentRequestDTO departmentCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createDepartment(departmentCreateDTO));
    }

    //READ ALL
    @Operation(summary = "Gets all departments", method = "GET")
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllDepartments());
    }

    //READ BY ID
    @Operation(summary = "Gets a department by its ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getDepartmentById(id));
    }

    //UPDATE
    @Operation(summary = "Updates a department by its id", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable UUID id, @Valid @RequestBody DepartmentRequestDTO departmentUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateDepartment(id, departmentUpdateDTO));
    }

    //DELETE
    @Operation(summary = "Deletes a department by its ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {
        service.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
