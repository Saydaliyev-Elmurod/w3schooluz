package com.example.w3schooluz.util

import com.example.w3schooluz.profile.ProfileRole
import com.fasterxml.jackson.annotation.JsonInclude

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter


@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class JwtDTO {
    var mail: String
    var role: ProfileRole? = null

    constructor(mail: String, role: ProfileRole?) {
        this.mail = mail
        this.role = role
    }

    private var id: Int? = null

    constructor(mail: String, id: Int?) {
        this.mail = mail
        this.id = id
    }
}
