package com.example.filmoteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfiguracion {
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new  WebMvcConfigurer() {


            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://filmoteca.psycode.es")
                        .allowedMethods("GET", "POST","PUT", "DELETE")
                        .maxAge(3600);
            }
            /*
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("http://localhost:9013")
                            .allowedMethods("GET", "POST","PUT", "DELETE")
                            .maxAge(3600);
                }
            */

        };
    }


}
