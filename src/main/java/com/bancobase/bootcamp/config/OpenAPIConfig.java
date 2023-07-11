package com.bancobase.bootcamp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Bootcamp UADY-BASE")
                .version("v0.0.1")
                .description("Proyecto demo para el bootcamp UADY-BASE.")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.bancobase.com/")
                )
        );
    }
}
