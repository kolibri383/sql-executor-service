package com.example.sqlexecutorservice.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime


 class ApiError {
    //private val sql: String? = null,
    var message: String? = null
    var status: HttpStatus? = null
    var timestamp: LocalDateTime? = null
    var path: String? = null
}