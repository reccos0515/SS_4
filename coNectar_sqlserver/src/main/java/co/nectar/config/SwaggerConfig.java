package co.nectar.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.nectar"))
                .paths(regex("\\/ben\\/(u|l|m).*"))
                .build()
                .apiInfo(metaInfo());
    }
	

	 private ApiInfo metaInfo() {

	        ApiInfo apiInfo = new ApiInfo(
	                "coNectar API",
	                "coNectar Social Media Application API",
	                "1.0",
	                "Terms of Service",
	                new Contact("SS_4 Server Team, Tristan Anderson and Ben Simon", "http://proj-309-ss-4.cs.iastate.edu/",
	                        "basimon@iastate.edu"),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html"
	        );

	        return apiInfo;
	    }
}