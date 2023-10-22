package com.tsayvyac.task.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Candidate database API",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Dev",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
