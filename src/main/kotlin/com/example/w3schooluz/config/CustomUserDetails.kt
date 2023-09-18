package com.example.w3schooluz.config

import com.example.w3schooluz.profile.ProfileEntity
import com.example.w3schooluz.profile.ProfileRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.List


class CustomUserDetails : UserDetails {
    var id: Int? = null
        private set

    constructor(id: Int?) {
        this.id = id
    }

    private var profileEntity: ProfileEntity? = null

    constructor(profileEntity: ProfileEntity?) {
        this.profileEntity = profileEntity
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        val role = profileEntity?.role
        val simpleGrantedAuthority = SimpleGrantedAuthority(role?.name)
        return List.of(simpleGrantedAuthority)
        //  return List.of(new SimpleGrantedAuthority(profileEntity.getRole().name()));
    }

    override fun getPassword(): String {
        return profileEntity?.password?:""
    }

    override fun getUsername(): String {
        return profileEntity?.email?:""
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getProfileEntity(): ProfileEntity? {
        return profileEntity
    }
}