package com.example.w3schooluz.sms


import com.example.w3schooluz.auth.sms.SendSmsEntity
import com.example.w3schooluz.auth.sms.SendSmsRepository
import com.example.w3schooluz.exp.SendSmsException
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*


@Service
class SmsSenderService(val sendSmsRepository: SendSmsRepository) {


    @Value("\${spring.sms.parol}")
    private val password: String? = null

    @Value("\${spring.sms.email}")
    private val email: String? = null
    fun sendSms(phone: String, content: String): Boolean {
        val token = token
        return if (token == null || token.isBlank()) {
            throw SendSmsException("Error with sms sender")
        } else {
            if (java.lang.Boolean.TRUE == send(phone, content, token)) {
                true
            } else {
                throw SendSmsException("Error wth sms sender")
            }
        }
    }

    private fun send(phone: String, content: String, token: String): Boolean? {
        try {
            val httpClient = HttpClient.newHttpClient()
            val code = generateCode()
            val body = String.format(
                """{    "mobile_phone":"%s",
    "message": "%s",
    "from": "4546",
    "callback_url": "http://0000.uz/test.php"
}""", phone, content + code
            )
            val request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create("https://notify.eskiz.uz/api/message/sms/send"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer $token")
                .build()
            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            println(response.statusCode())
            if (response.statusCode() == 200) {
                sendSmsRepository?.save(SendSmsEntity())
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        return null
    }

    private fun generateCode(): String {
        val random = Random()
        return random.nextInt(100000, 999999).toString()
    }

    private val token: String?
        private get() {
            try {
                val httpClient = HttpClient.newHttpClient()
                val json = StringBuilder()
                    .append("{")
                    .append("\"email\":\"")
                    .append(email)
                    .append("\",\"password\":\"")
                    .append(password)
                    .append("\"")
                    .append("}").toString()
                val request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .uri(URI.create("https://notify.eskiz.uz/api/auth/login"))
                    .header("Content-Type", "application/json")
                    .build()
                val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
                println(response.statusCode())
                if (response.statusCode() == 200) {
                    val gson = Gson()
                    val dto: TokenResponseDto = gson.fromJson(response.body(), TokenResponseDto::class.java)
                    return dto.data?.token
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
            return null
        }
}
