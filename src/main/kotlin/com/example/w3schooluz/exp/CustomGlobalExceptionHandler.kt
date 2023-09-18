//package com.example.w3schooluz.exp
//
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatusCode
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.MethodArgumentNotValidException
//import org.springframework.web.bind.annotation.ControllerAdvice
//import org.springframework.web.context.request.WebRequest
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
//import java.util.*
//
//
//@ControllerAdvice
//class CustomGlobalExceptionHandler : ResponseEntityExceptionHandler() {
//    protected fun handleMethodArgumentNotValid(
//        ex: MethodArgumentNotValidException,
//        headers: HttpHeaders?,
//        status: HttpStatusCode, request: WebRequest?
//    ): ResponseEntity<Any?> {
//        val body: MutableMap<String, Any> = LinkedHashMap()
//        body["timestamp"] = Date()
//        body["status"] = status.value()
//        val errors: MutableList<String?> = LinkedList()
//        for (error in ex.bindingResult!!.fieldErrors) {
//            errors.add(error.defaultMessage)
//        }
//        body["errors"] = errors
//        return ResponseEntity(body, headers, status)
//    }
//}