package br.com.dbc.vemser.terrativa.config;

import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("Pessoa API")
                        .description("Documentação da API de Pessoa")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
