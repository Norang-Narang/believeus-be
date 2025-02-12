package com.example.believeus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Believeus API Documentation")
                        .description("요양보호사와 어르신을 연결하는 돌봄 매칭 서비스 API 문서입니다.")
                        .version("v1.0"));
    }
}