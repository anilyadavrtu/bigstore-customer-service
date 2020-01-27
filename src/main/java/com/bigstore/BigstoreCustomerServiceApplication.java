package com.bigstore;

import com.bigstore.util.LoggingInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class BigstoreCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigstoreCustomerServiceApplication.class, args);
	}


	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
	}


	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	private ApiInfo generateApiInfo()
	{
		return new ApiInfo(
				"bigstore customer Service", "bigstore customer Service is to manage the customer operations.", "Version 1.0 - mw",
				"urn:tos", "anilyadav.rtu@gmail.com", "Apache 2.0", "https://www.linkedin.com/in/anil-yadav-0346a249/");
	}
}
