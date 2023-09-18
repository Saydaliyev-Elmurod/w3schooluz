package com.example.w3schooluz.config

import com.example.w3schooluz.profile.ProfileEntity
import com.example.w3schooluz.profile.ProfileRepository
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
@AllArgsConstructor
class CustomUserDetailsService(val profileRepository: ProfileRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        // adminjon_mazgi@gmail.com
        val optional: Optional<ProfileEntity> = profileRepository.findByEmail(username)
        if (optional.isEmpty) {
            throw UsernameNotFoundException("User not found")
        }
        val profile: ProfileEntity = optional.get()
        return CustomUserDetails(profile)
    }
}