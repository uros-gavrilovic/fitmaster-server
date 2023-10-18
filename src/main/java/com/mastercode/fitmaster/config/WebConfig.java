package com.mastercode.fitmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        // Specify all what is coming from the frontend
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:3000"); // React frontend
        configuration.addAllowedOrigin("http://localhost:19006"); // Mobile application
        configuration.addAllowedOrigin(
                "https://victorious-flower-0f7326e10.3.azurestaticapps.net"); // Front-End URL (Azure)
        configuration.addAllowedOrigin("*"); // All origins
        configuration.setAllowedHeaders(
                Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
        configuration.setAllowedMethods(
                Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name()));
        // Time the Options request is accepted
        configuration.setMaxAge(3600L); // 30 min

        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
