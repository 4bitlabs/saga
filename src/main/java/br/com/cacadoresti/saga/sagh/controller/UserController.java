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

import br.com.cacadoresti.saga.sagh.model.dto.user.UserRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.user.UserResponseDTO;
import br.com.cacadoresti.saga.sagh.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "Users Management", description = "Endpoints for managing users")
@SecurityRequirement(name = "jwtAuthentication")
//TODO: Complete documentation
public class UserController {
    private final UserService service;
    public UserController(UserService userService) {
        this.service = userService;
    }

    //CREATE
    @Operation(summary = "Creates a new user", method = "POST")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(userCreateDTO));
    }

    //READ ALL
    @Operation(summary = "Gets all users", method = "GET")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
    }

    //READ BY ID
    @Operation(summary = "Gets user by id", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    //UPDATE
    @Operation(summary = "Updates a user", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO userUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, userUpdateDTO));
    }

    //DELETE
    @Operation(summary = "Deletes a user", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
