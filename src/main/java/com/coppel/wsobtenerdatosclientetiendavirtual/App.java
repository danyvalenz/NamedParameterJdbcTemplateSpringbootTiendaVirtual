package com.coppel.wsobtenerdatosclientetiendavirtual;

import com.coppel.wsobtenerdatosclientetiendavirtual.config.AppConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class App {

    @Autowired
    private AppConfig config;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
                                "Access-Control-Allow-Methods", "Accept", "Authorization", "Content-Type", "Method",
                                "Origin", "X-Forwarded-For", "X-Real-IP");
            }
        };
    }

}
