package br.com.cacadoresti.saga;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "SAGA",
		version = "1.0.0",
		description = "Sistema Acadêmico de Gestão e Alocação"
	)
)
@SpringBootApplication
@SecurityScheme(name = "jwtAuthentication", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SagaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SagaApplication.class, args);
	}
}
