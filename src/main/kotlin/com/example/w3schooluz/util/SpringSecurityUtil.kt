package com.example.w3schooluz.util


import com.example.w3schooluz.config.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails


object SpringSecurityUtil {
    val profileId: Int
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            val userDetails = authentication.principal as CustomUserDetails
            return userDetails.getProfileEntity()?.id!!
        }
    val currentUserId: String
        get() {
            val authentication =
                SecurityContextHolder.getContext().authentication
            return authentication.name
        }
    val userDetails: UserDetails
        get() {
            val authentication =
                SecurityContextHolder.getContext().authentication
            return authentication.principal as UserDetails
        }
}
