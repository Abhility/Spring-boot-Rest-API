package com.mindtree.springbootapi.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class AppConfig {

	@Value("${app.allowed-origins:${app.default-origin}}")
	private String[] allowedOrigins;

	@Value("${spring.profiles.active:default}")
	private String activeProfile;

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				logger.info("Active profile:: {}",activeProfile);
				logger.info("Number of allowed origins:: {}",allowedOrigins.length);
				logger.info("Allowed Origins::");
				for(String origin: allowedOrigins)
					logger.info("--> {}",origin);
				registry.addMapping("/**").allowedOrigins(allowedOrigins);
			}
		};
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				   .select()
				   .apis(RequestHandlerSelectors.basePackage("com.mindtree.springbootapi"))
				   .build()
				   .apiInfo(apiDetails());
		
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Sample Spring boot api",
				"This is a sample spring boot api",
				"1.0.0", 
				"#", 
				new Contact("Abhishek", "#","abhishek6596@gmail.com"),
				"Sample",
				"#",Collections.emptyList());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder(10);
	}
}
