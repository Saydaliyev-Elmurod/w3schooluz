package com.example.w3schooluz.auth

import com.example.w3schooluz.profile.ProfileEntity
import com.example.w3schooluz.profile.ProfileRepository
import com.example.w3schooluz.profile.ProfileRole
import com.example.w3schooluz.sms.SmsContent
import com.example.w3schooluz.sms.SmsSenderService
import com.example.w3schooluz.util.JwtUtil
import com.example.w3schooluz.util.MD5Util.convertToMD5
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
@AllArgsConstructor
class AuthService(
    val profileRepository: ProfileRepository,
    val smsSenderService: SmsSenderService
) {
    fun signUp(dto: AuthRequestDto): ResponseEntity<*> {
//        if (isValidPhone(dto.phone)) {
            val entity = ProfileEntity(
                role = ProfileRole.ROLE_ADMIN,
                name = dto.name,
                surname = dto.surname,
                password = convertToMD5(dto.password),
                email = dto.email
            )
            profileRepository.save(entity)
//            val result = smsSenderService!!.sendSms(dto.phone.toString(), SmsContent.REGISTER_CODE_CONTENT)
//        }
        return ResponseEntity.ok().build<Any>()
    }

    private fun isValidPhone(phone: String?): Boolean {
        return phone?.startsWith("998") == true && phone?.length == 12
    }

    fun signIn(dto: AuthLoginDto): ResponseEntity<*> {
        if (dto.email == "admin@gmail.com" && dto.password == "admin") {
           return ResponseEntity.ok(
                JwtUtil.generateJwt(
                    email = dto.email,
                    role = ProfileRole.ROLE_ADMIN,
                    password = dto.password
                )
            )
        }
        return ResponseEntity.ok("Email or password incorrect")
    }
}
