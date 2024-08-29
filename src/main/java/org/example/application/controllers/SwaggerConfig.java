package org.example.application.controllers;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger API documentation.
 * This class sets up the OpenAPI configuration and groups the API documentation into separate categories
 * for cars, users, and orders.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Provides custom OpenAPI configuration for the application.
     *
     * @return an OpenAPI instance with general API information.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Car, User and Order Management API")
                        .version("1.0")
                        .description("API for managing cars, users, and orders"));
    }

    /**
     * Configures Swagger documentation for car-related endpoints.
     *
     * @return a GroupedOpenApi instance that includes all endpoints under "/car/**".
     */
    @Bean
    public GroupedOpenApi carApi() {
        return GroupedOpenApi.builder()
                .group("car-public")
                .pathsToMatch("/car/**")
                .build();
    }

    /**
     * Configures Swagger documentation for user-related endpoints.
     *
     * @return a GroupedOpenApi instance that includes all endpoints under "/user/**".
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user-public")
                .pathsToMatch("/user/**")
                .build();
    }

    /**
     * Configures Swagger documentation for order-related endpoints.
     *
     * @return a GroupedOpenApi instance that includes all endpoints under "/order/**".
     */

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
                .group("order-public")
                .pathsToMatch("/order/**")
                .build();
    }
}
