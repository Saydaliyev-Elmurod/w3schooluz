package com.example.w3schooluz.util


import com.example.w3schooluz.exp.MethodNotAllowedException
import com.example.w3schooluz.profile.ProfileRole
import io.jsonwebtoken.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import java.util.*


@Service
object JwtUtil {
    private const val tokenLiveTime = 1000 * 3600 * 24 // 1-day
    private const val secretKey = "dasda143mazgi"
    fun encode(email: String?, role: ProfileRole?): String {
        val jwtBuilder: JwtBuilder = Jwts.builder()
        jwtBuilder.setIssuedAt(Date())
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey)
        jwtBuilder.claim("email", email)
        jwtBuilder.claim("role", role)
        jwtBuilder.setExpiration(Date(System.currentTimeMillis() + tokenLiveTime))
        jwtBuilder.setIssuer("Kunuz test portali")
        return jwtBuilder.compact()
    }

    fun encodeToUpdateEmail(email: String?, pId: Int?): String {
        val jwtBuilder: JwtBuilder = Jwts.builder()
        jwtBuilder.setIssuedAt(Date())
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey)
        jwtBuilder.claim("email", email)
        jwtBuilder.claim("id", pId)
        jwtBuilder.setExpiration(Date(System.currentTimeMillis() + tokenLiveTime))
        jwtBuilder.setIssuer("Kunuz test portali")
        return jwtBuilder.compact()
    }

    fun generateJwt(email: String?, password: String?, role: ProfileRole?): String {
        val jwtBuilder: JwtBuilder = Jwts.builder()
        jwtBuilder.setIssuedAt(Date())
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey)
        jwtBuilder.claim("email", email)
        jwtBuilder.claim("password", password)
        jwtBuilder.claim("role", role)
        jwtBuilder.setExpiration(Date(System.currentTimeMillis() + tokenLiveTime))
        jwtBuilder.setIssuer("Kunuz test portali")
        return jwtBuilder.compact()
    }

    fun decode(token: String?): JwtDTO {
        val jwtParser: JwtParser = Jwts.parser()
        jwtParser.setSigningKey(secretKey)
        val jws: Jws<Claims> = jwtParser.parseClaimsJws(token)
        val claims: Claims = jws.getBody()
        val email = claims.get("email") as String
        val role = claims.get("role") as String
        val profileRole = ProfileRole.valueOf(role)
        return JwtDTO(email, profileRole)
    }

    fun decodeToUpdateEmail(token: String?): JwtDTO {
        val jwtParser: JwtParser = Jwts.parser()
        jwtParser.setSigningKey(secretKey)
        val jws: Jws<Claims> = jwtParser.parseClaimsJws(token)
        val claims: Claims = jws.getBody()
        val email = claims.get("email") as String
        val pId = claims.get("id") as Int
        return JwtDTO(email, pId)
    }

    fun decodeEmailVerification(token: String?): String {
        try {
            val jwtParser: JwtParser = Jwts.parser()
            jwtParser.setSigningKey(secretKey)
            val jws: Jws<Claims> = jwtParser.parseClaimsJws(token)
            val claims: Claims = jws.getBody()
            return claims["email"].toString()
        } catch (e: JwtException) {
            e.printStackTrace()
        }
        throw MethodNotAllowedException("Jwt exception")
    }

    fun getJwtDTO(authorization: String): JwtDTO {
        val str = authorization.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val jwt = str[1]
        return decode(jwt)
    }

    fun getJwtDTO(authorization: String, vararg roleList: ProfileRole?): JwtDTO {
        val str = authorization.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val jwt = str[1]
        val jwtDTO = decode(jwt)
        var roleFound = false
        for (role in roleList) {
            if (jwtDTO.role == role) {
                roleFound = true
                break
            }
        }
        if (!roleFound) {
            throw MethodNotAllowedException("Method not allowed")
        }
        return jwtDTO
    }

    fun checkForRequiredRole(request: HttpServletRequest, vararg roleList: ProfileRole) {
        val jwtRole = request.getAttribute("role") as ProfileRole
        var roleFound = false
        for (role in roleList) {
            if (jwtRole == role) {
                roleFound = true
                break
            }
        }
        if (!roleFound) {
            throw MethodNotAllowedException("Method not allowed")
        }
    }
}
