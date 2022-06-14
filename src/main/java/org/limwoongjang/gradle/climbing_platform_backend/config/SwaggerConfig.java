package org.limwoongjang.gradle.climbing_platform_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author : Limjunho
 * @Date : 2021-12-22
 * @desc Swagger 설정
 */

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30)
        // .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors
            .basePackage("org.limwoongjang.gradle.climbing_platform_backend.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Swagger docs")
        .description("climbing platform backend API 정리")
        .version("1.0")
        .build();
  }
}