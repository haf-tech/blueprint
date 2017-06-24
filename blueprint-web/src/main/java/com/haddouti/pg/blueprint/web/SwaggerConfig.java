package com.haddouti.pg.blueprint.web;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Note").apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/note.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Note Maintenance").description("Note Maintenance")
				.termsOfServiceUrl("http://example.org").contact(new Contact("Hafid Haddouti", "", ""))
				.license("Apache License Version 2.0").licenseUrl("exmaple.org/LICENSE").version("2.0").build();
	}
}
