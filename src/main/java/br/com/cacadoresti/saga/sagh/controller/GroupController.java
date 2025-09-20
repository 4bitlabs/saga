package br.com.cacadoresti.saga.sagh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cacadoresti.saga.sagh.model.dto.group.GroupRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.group.GroupResponseDTO;
import br.com.cacadoresti.saga.sagh.service.GroupService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/groups")
@Tag(name = "Groups", description = "Endpoints for groups management")
@SecurityRequirement(name = "jwtAuthentication")
//TODO: Complete documentation
public class GroupController {
    private final GroupService service;

    public GroupController(GroupService groupService) {
        this.service = groupService;
    }

    //CREATE
    @Operation(summary = "Creates a group", method = "POST")
    @PostMapping
    public ResponseEntity<GroupResponseDTO> createCourse(@Valid @RequestBody GroupRequestDTO groupCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createGroup(groupCreateDTO));
    }

    //READ ALL
    @Operation(summary = "Gets all groups", method = "GET")
    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> getAllGroups() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllGroups());
    }

    //READ BY ID
    @Operation(summary = "Gets a group by its ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> getGroupById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getGroupById(id));
    }

    //UPDATE
    @Operation(summary = "Updates a group by its ID", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@PathVariable UUID id, @Valid @RequestBody GroupRequestDTO groupUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateGroup(id, groupUpdateDTO));
    }

    //DELETE
    @Operation(summary = "Deletes a group by its ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID id) {
        service.deleteGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //ADDS A PROFESSOR TO GROUP
    @Operation(summary = "Adds a professor (given its siape code) to a group (given its ID)", method = "PATCH")
    @PatchMapping("/{id}/addProfessor/{siape}")
    public ResponseEntity<GroupResponseDTO> addsProfessorToGroup(@PathVariable UUID id, @PathVariable(value = "siape") String siapeCode) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addsProfessorToGroup(id, siapeCode));
    }

    //REMOVES A PROFESSOR FROM GROUP
    @Operation(summary = "Removes a professor (given its siape code) from a group (given its ID)", method = "PATCH")
    @PatchMapping("/{id}/removeProfessor/{siape}")
    public ResponseEntity<GroupResponseDTO> removesProfessorFromGroup(@PathVariable UUID id, @PathVariable(value = "siape") String siapeCode) {
        return ResponseEntity.status(HttpStatus.OK).body(service.removesProfessorFromGroup(id, siapeCode));
    }

    //ADDS A STUDENT TO GROUP
    @Operation(summary = "Adds a student (given its enrollment code) to a group (given its ID)", method = "PATCH")
    @PatchMapping("/{id}/addStudent/{enrollment}")
    public ResponseEntity<GroupResponseDTO> addsStudentToGroup(@PathVariable UUID id, @PathVariable(value = "enrollment") String enrollmentCode) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addsStudentToGroup(id, enrollmentCode));
    }

    //REMOVES A STUDENT FROM GROUP
    @Operation(summary = "Removes a student (given its enrollment code) from a group (given its ID)", method = "PATCH")
    @PatchMapping("/{id}/removeStudent/{enrollment}")
    public ResponseEntity<GroupResponseDTO> removesStudentFromGroup(@PathVariable UUID id, @PathVariable(value = "enrollment") String enrollmentCode) {
        return ResponseEntity.status(HttpStatus.OK).body(service.removesStudentFromGroup(id, enrollmentCode));
    }

    //ADDS PROFESSORS TO GROUP
    @Operation(summary = "Adds professors (given their siape codes) to a group (given its ID)", method = "PATCH")
    @PatchMapping("/{id}/addProfessors")
    public ResponseEntity<GroupResponseDTO> addsProfessorsToGroup(@PathVariable UUID id, @RequestBody List<String> professorsSiapeCodes) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addsProfessorsToGroup(id, professorsSiapeCodes));
    }

    //ADDS STUDENTS TO GROUP
    @Operation(summary = "Adds students (given their enrollment codes) to a group (given its ID)", method = "PATCH")
    @PatchMapping("/{id}/addStudents")
    public ResponseEntity<GroupResponseDTO> addsStudentsToGroup(@PathVariable UUID id, @RequestBody List<String> studentsEnrollmentCodes) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addsStudentsToGroup(id, studentsEnrollmentCodes));
    }
}
