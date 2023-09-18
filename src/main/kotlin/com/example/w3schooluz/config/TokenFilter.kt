package com.example.w3schooluz.config

import com.example.w3schooluz.util.JwtDTO
import com.example.w3schooluz.util.JwtUtil
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*


@Component
class TokenFilter : OncePerRequestFilter() {
    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Throws(ServletException::class)
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val pathMatcher = AntPathMatcher()
        return Arrays.asList<Any>(SecurityConfig.AUTH_WHITELIST).stream()
            .anyMatch { p: Any? ->
                val match = pathMatcher.match(p.toString(), request.servletPath)
                match
            }
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        val token = authHeader.substring(7)
        val jwtDto: JwtDTO
        try {
            jwtDto = JwtUtil.decode(token)
            val userDetails = userDetailsService!!.loadUserByUsername(jwtDto.mail)
            val authentication = UsernamePasswordAuthenticationToken(
                userDetails,
                null, userDetails.authorities
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        } catch (e: JwtException) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.setHeader("Message", "Token Not Valid")
            return
        }
    }
}