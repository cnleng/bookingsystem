package com.flexter.bookingsystem.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI myOpenAPI() {

    License mitLicense = new License().name("MIT License").url("");
    Info info = new Info()
        .title("Fast & Flexter Service")
        .version("0.0.1")
        .description("This API exposes endpoints to manage Fast&Flexter Booking System.").termsOfService("")
        .license(mitLicense);

    return new OpenAPI().info(info);
  }

}
