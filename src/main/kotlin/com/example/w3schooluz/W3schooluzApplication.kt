package com.example.w3schooluz

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(info = Info(title = "Online shop API", version = "1.0", description = ""))
@SecurityScheme(
    name = "learn center",
    scheme = "Bearer",
    type = SecuritySchemeType.HTTP,
    `in` = SecuritySchemeIn.HEADER
)
@SpringBootApplication
class W3schooluzApplication


fun main(args: Array<String>) {
    runApplication<W3schooluzApplication>(*args)
}
