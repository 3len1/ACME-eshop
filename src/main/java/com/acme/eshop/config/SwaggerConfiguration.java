package com.acme.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * Created by Eleni on 5/19/2018.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiConfig() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.acme.eshop.controller"))
                .paths(PathSelectors.any())
                .build();

        docket.apiInfo(apiInfo());
        return docket;
    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("Koyot team",
                "https://github.com/3len1/SpringRestApi", "vseleni@gmail.com");
        return new ApiInfo("ACME e-shop",
                "Spring Rest API - Code.Hub",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
