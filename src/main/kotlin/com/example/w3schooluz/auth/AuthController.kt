package com.example.w3schooluz.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("auth")
@AllArgsConstructor
@SecurityRequirement(name = "Auth ")
class AuthController(val authService: AuthService) {
    @Operation(description = "User registratsiyadan otishi uchun api ")
    @ApiResponse(
        responseCode = "200",
        content = arrayOf(
            Content(
                schema = Schema(implementation = AuthRequestDto::class),
                mediaType = "application/json"
            )
        )
    )
    @ApiResponse(responseCode = "400", description = "NOT FOUND")
    @PostMapping("sign-up")
    fun signUp(@RequestBody dto: AuthRequestDto): ResponseEntity<*> {
        return authService.signUp(dto)
    }

    @Operation(description = "Sign-in user ")
    @ApiResponse(
        responseCode = "200",
    )
    @PostMapping("sign-in")
    fun signIn(@RequestBody dto: AuthLoginDto): ResponseEntity<*> {
        return authService.signIn(dto)
    }
}