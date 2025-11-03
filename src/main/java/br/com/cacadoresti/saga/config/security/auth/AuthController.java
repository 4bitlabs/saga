package br.com.cacadoresti.saga.config.security.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cacadoresti.saga.config.security.auth.jwt.JwtDTO;
import br.com.cacadoresti.saga.sagh.model.dto.user.UserLoginDTO;

@RestController
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequestMapping("/v1/auth")
@Tag(name = "Auth", description = "Endpoints for auth related services")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService authService) {
        this.service = authService;
    }

    //Authentication
    @Operation(summary = "Authenticates a user using a username and password", method = "POST")
    @PostMapping
    public ResponseEntity<JwtDTO> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.authenticateUser(userLoginDTO));
    }

}
