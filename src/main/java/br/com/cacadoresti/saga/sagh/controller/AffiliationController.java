package br.com.cacadoresti.saga.sagh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cacadoresti.saga.sagh.model.dto.affiliation.AffiliationResponseDTO;
import br.com.cacadoresti.saga.sagh.service.AffiliationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/affiliations")
@Tag(name = "Affiliations", description = "Endpoints for affiliations management")
@SecurityRequirement(name = "jwtAuthentication")
public class AffiliationController {
    private final AffiliationService service;
    public AffiliationController(AffiliationService affiliationService) {
        this.service = affiliationService;
    }

    //GET ALL
    @Operation(summary = "Gets all affiliations", method = "GET")
    @GetMapping
    public ResponseEntity<List<AffiliationResponseDTO>> getAllAffiliations() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAffiliations());
    }

    //GET ALL BY USER ID
    @Operation(summary = "Gets all affiliations of a particular user", method = "GET")
    @GetMapping("/{userId}")
    public ResponseEntity<List<AffiliationResponseDTO>> getAllAffiliationsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAffiliationsByUserId(userId));
    }
}
