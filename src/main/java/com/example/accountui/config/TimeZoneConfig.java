package com.example.accountui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class TimeZoneConfig implements WebMvcConfigurer {

    @PostConstruct
    public void init() {
        // Définir le fuseau horaire par défaut de l'application
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Bean
    public ZoneId defaultZoneId() {
        return ZoneId.of("Europe/Paris");
    }
}