package com.example.w3schooluz.config

import com.example.w3schooluz.util.MD5Util
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfigurationSource


@EnableWebSecurity
@Component
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    val userDetailsService: UserDetailsService,
    val tokenFilter: TokenFilter,
    val corsConfigurationSource: CorsConfigurationSource
) {
    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .cors { cors: CorsConfigurer<HttpSecurity?> ->
//                cors.configurationSource(
//                    corsConfigurationSource
//                )
//            }
//            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
//            .sessionManagement { sessionManagement: SessionManagementConfigurer<HttpSecurity?> ->
//                sessionManagement.sessionCreationPolicy(
//                    SessionCreationPolicy.STATELESS
//                )
//            }
//            .formLogin { obj: FormLoginConfigurer<HttpSecurity> -> obj.disable() }
//            .httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
//            .authorizeHttpRequests { auth ->
//                auth
//                    .requestMatchers(
//                        "/",
//                        "/error",
//                        "/favicon.ico",
//                        "*.png",
//                        "*.gif",
//                        "*.svg",
//                        "*.jpg",
//                        "*.html",
//                        "*.css",
//                        "*.js"
//                    )
//                    .permitAll()
//                    .requestMatchers(
//                        "/swagger-ui/**", "/swagger-resources/**",
//                        "/swagger-resources", "/configuration/ui", "/v2/api-docs",
//                        "/configuration/ui",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**",
//                        "/v3/api-docs/**",
//
//                        )
//                    .permitAll()
//                    .requestMatchers("/auth/**", "/oauth2/**")
//                    .permitAll()
//                    .requestMatchers(*AUTH_WHITELIST)
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//            }
//        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
//        return http.build()
        http
            .cors { cors: CorsConfigurer<HttpSecurity?> ->
                cors.configurationSource(
                    corsConfigurationSource
                )
            }
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .sessionManagement { sessionManagement: SessionManagementConfigurer<HttpSecurity?> ->
                sessionManagement.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .formLogin { obj: FormLoginConfigurer<HttpSecurity> -> obj.disable() }
            .httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
            .authorizeHttpRequests(
                Customizer { auth ->
                    auth
                        .requestMatchers(
                            "/",
                            "/error",
                            "/favicon.ico",
                            "*.png",
                            "*.gif",
                            "*.svg",
                            "*.jpg",
                            "*.html",
                            "*.css",
                            "*.js"
                        )
                        .permitAll()
                        .requestMatchers(*AUTH_WHITELIST)
                        .permitAll()
                        .requestMatchers("/auth/**", "/oauth2/**", "/attach/open/**", "/product/**", "/category/**")
                        .permitAll()
                        .requestMatchers("/admin/**")
                        .hasAnyRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                }
            )

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    //        @Bean
    fun passwordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            override fun encode(rawPassword: CharSequence): String {
                return rawPassword.toString()
            }

            override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
                return MD5Util.getMd5Hash(rawPassword.toString()) == encodedPassword
            }
        }
    }

    companion object {
        var AUTH_WHITELIST = arrayOf(
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**"
        )
    }
}