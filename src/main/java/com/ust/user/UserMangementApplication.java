package com.ust.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMangementApplication.class, args);
	}
	@Bean
	public ApiInfo apiInfo() {
		ApiInfoBuilder apiBuilder = new ApiInfoBuilder();
		apiBuilder.title("Spring Boot Swagger Application").version("1.0").license("(c) Stackroute")
				.description("List of All Endpoints used in User RestFul API");
		return apiBuilder.build();
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
				.pathMapping("/").apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}
}
