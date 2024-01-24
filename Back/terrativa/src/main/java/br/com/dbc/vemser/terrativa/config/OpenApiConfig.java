package br.com.dbc.vemser.terrativa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("TerrAtiva API")
                        .description("Documentação da API da plataforma TerrAtiva")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                    .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
