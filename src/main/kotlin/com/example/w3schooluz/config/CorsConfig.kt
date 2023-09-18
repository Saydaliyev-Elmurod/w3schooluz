package com.example.w3schooluz.config

import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
class CorsConfig {
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        //configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.allowedOriginPatterns = listOf("*")
        configuration.allowedMethods = listOf("DELETE", "GET", "POST", "PATCH", "PUT", "OPTIONS")
        configuration.allowCredentials = true
        configuration.allowedHeaders = listOf(
            "Access-Control-Allow-Headers",
            "Access-Control-Allow-Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers",
            "Origin", "Cache-Control",
            "Content-Type",
            "Authorization"
        )
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}