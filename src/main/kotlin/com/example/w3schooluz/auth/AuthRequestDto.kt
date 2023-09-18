package com.example.w3schooluz.auth

import com.example.w3schooluz.profile.ProfileType
import lombok.Getter
import lombok.Setter



class AuthRequestDto {
     val name: String? = null
     val surname: String? = null
     val type: ProfileType? = null
     val email: String? = null
     val password: String? = null
}