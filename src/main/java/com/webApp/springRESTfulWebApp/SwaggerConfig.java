package com.webApp.springRESTfulWebApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    private Contact contact = new Contact(
            "Marko Pavlicic",
            "http://www.apache.org/licenses/LICENSE-2.0",
            "noreply@test.com");

    private List<VendorExtension> vendorExtensions = new ArrayList<>();


    private ApiInfo apiInfo = new ApiInfo(
            "Spring RESTful Web App documentation",
            "documentation for Web Service endpoints",
            "1.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            vendorExtensions);
    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList("HTTP", "HTTPs")))
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.webApp.springRESTfulWebApp"))
                .paths(PathSelectors.any())
                .build();
    }
}
