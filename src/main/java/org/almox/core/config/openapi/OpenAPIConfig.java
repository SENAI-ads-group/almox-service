package org.almox.core.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "ALMOX API", version = "2.0", description = "API de gerenciamento de almoxarifado"))
public class OpenAPIConfig {
}
