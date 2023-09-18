package com.example.w3schooluz.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object MD5Util {
    fun encode(password: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            val array = md.digest(password.toByteArray())
            val sb = StringBuffer()
            for (i in array.indices) {
                sb.append(Integer.toHexString(array[i].toInt() and 0xFF or 0x100).substring(1, 3))
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
        }
        return null
    }

    fun getMd5Hash(input: String): String {
        try {
            val md = MessageDigest.getInstance("MD5")
            val messageDigest = md.digest(input.toByteArray())
            val no = BigInteger(1, messageDigest)
            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    fun convertToMD5(input: String?): String? {
        var md5: String? = null
        if (null == input) return null
        try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(input.toByteArray(), 0, input.length)
            md5 = BigInteger(1, digest.digest()).toString(16)
        } catch (e: NoSuchAlgorithmException) {
        }
        return md5
    }
}