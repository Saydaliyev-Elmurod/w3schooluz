package com.example.w3schooluz.config

import java.util.List
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean


class ApiConfig {
    @Value("\${server.host}")
    private val url: String? = null
    @Bean
    fun myOpenAPI(): OpenAPI {
        val devServer = Server()
        val contact = Contact()
        contact.setEmail("kun.uz")
        contact.setName("BezKoder")
        contact.setUrl("https://www.bezkoder.com")
        val info: Info = Info()
            .title("Kun uz Management API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage tutorials.")
            .termsOfService("https://www.bezkoder.com/terms")
            .license(null)
        return OpenAPI().info(info).servers(List.of(devServer))
    }
}