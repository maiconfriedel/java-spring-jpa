package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication()
@ComponentScan({"com.example"})
@EntityScan("com.example.infrastructure.models")
@EnableJpaRepositories("com.example.infrastructure.repositories")
public class DemoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringApplication.class, args);
	}
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(
					new Info()
					.title("Spring Sample API")
					.description("Exemplo de API com Spring, documentando com swagger")
					.version("v0.0.1")
					.license(new License().name("Apache 2.0").url("http://springdoc.org"))
					.contact(new Contact().email("maicon.friedel@gmail.com").url("https://github.com/maiconfriedel"))
				);
	}
	
	@Configuration
	public class WebConfig implements WebMvcConfigurer {
	    @Override
	    public void configurePathMatch(PathMatchConfigurer configurer) {
	        AntPathMatcher matcher = new AntPathMatcher();
	        matcher.setCaseSensitive(false);
	        configurer.setPathMatcher(matcher);
	    }
	}

}