package com.configs;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.hibernate.cfg.AvailableSettings;
@Configuration
public class AppConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return props -> {
            // Store all enums as strings globally
            props.put(AvailableSettings.PREFER_NATIVE_ENUM_TYPES, "string");

            // Use nationalized character data for all strings
            props.put(AvailableSettings.USE_NATIONALIZED_CHARACTER_DATA, true);

            // Alternative enum configuration
            props.put("hibernate.type.default_enum_type", "string");
        };
    }
}
