package com.example.Sistema.de.Gerenciamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // Especifica o padrão OpenAPI 3.0
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.exemplo.sistemagerenciamento.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sistema de Gerenciamento API")
                .description("API REST para gerenciamento de recursos com Spring Boot")
                .version("1.0.0")
                .build();
    }
}