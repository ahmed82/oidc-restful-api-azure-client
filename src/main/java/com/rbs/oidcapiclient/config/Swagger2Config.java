package com.rbs.oidcapiclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class Swagger2Config {

	@Bean
	public Docket taskApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiEndPointsInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rbs.botmanager"))
				.build();
	}

	@SuppressWarnings("deprecation")
	private static ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Bot Management Application")
				.description("API for managing Database transaction, Security as Restful Api")
				.contact("Ahmed.Al-Salih@retailbusinessservices.com")
				.license("RBS - Delhaize America")
				.termsOfServiceUrl("")
				.version("2.0.0")
				.build();
	}
}
