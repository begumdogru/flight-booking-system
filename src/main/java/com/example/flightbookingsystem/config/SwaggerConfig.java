package com.example.flightbookingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.flightbookingsystem.controller"))
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/api"); // Set the custom base path here
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Flight Booking System")
                .description("API")
                .version("1.0")
                .contact(new Contact("Begum Dogru", "https://example.com", "begum.do@example.com"))
                .build();
    }
}
