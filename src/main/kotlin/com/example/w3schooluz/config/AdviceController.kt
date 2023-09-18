package com.example.w3schooluz.config

import com.example.w3schooluz.exp.AppBadRequestException
import com.example.w3schooluz.exp.ItemNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.MethodNotAllowedException


@ControllerAdvice
class AdviceController {
    @ExceptionHandler(*[AppBadRequestException::class, ItemNotFoundException::class, MethodNotAllowedException::class])
    fun handleException(e: RuntimeException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(*[RuntimeException::class])
    fun handleNotValidException(e: RuntimeException): ResponseEntity<*> {
        e.printStackTrace()
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<String>(e.message)
    }
}