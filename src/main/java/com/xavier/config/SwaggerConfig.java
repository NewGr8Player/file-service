package com.xavier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author NewGr8Player
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xavier.api"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("File Service API")
                .description("File Service FastDFS BASED API ")
                .contact("NewGr8Player")
                .version("0.0.1-SNAPSHOT")
                .license("MIT License")
                .licenseUrl("https://github.com/NewGr8Player/file-service/blob/master/LICENSE")
                .build();
    }

}
