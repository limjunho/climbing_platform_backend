package org.limwoongjang.gradle.climbing_platform_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Limjunho
 * @date 2021-12-23
 * @desc CORS 모두 허용
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "PUT", "POST", "DELETE")
        .allowedOrigins("*");
  }
}