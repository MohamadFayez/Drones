package com.musala.drones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	  public OpenAPI openAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Drone Delivery System")
	              .description("There is a major new technology that is destined to be a disruptive force in the field of transportation")
	              .version("1.0.0")
	              .license(new License().name("Musala Soft 1.0")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Drone Delivery System"));
	  }

}