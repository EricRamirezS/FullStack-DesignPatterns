package com.aethelgard.archive.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public OpenAPI aesthgardOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Grimorio Mágico de Aethelgard")
                        .description("### Portales de acceso a la Biblioteca Etérea\n" +
                                     "Bienvenido, Archivista. Aquí residen los tomos sagrados, las pócimas prohibidas y los catálogos del oráculo.\n" +
                                     "**Usa estos conocimientos con sabiduría.**")
                        .version("Volumen I - 0.0.1")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Alto Consejo de Aethelgard")
                                .email("magister@aethelgard.magic")));
    }

    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }
}
