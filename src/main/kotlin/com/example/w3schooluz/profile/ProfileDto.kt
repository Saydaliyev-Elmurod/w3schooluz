package com.example.w3schooluz.profile

import lombok.Getter
import lombok.Setter



class ProfileDto {
     var id: Int? = null
     var name: String? = null
     var surname: String? = null
     var email: String? = null
     var phone: String? = null
     var password: String? = null
     var role: ProfileRole? = null
     var status: ProfileStatus? = null
}