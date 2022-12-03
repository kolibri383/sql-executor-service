package com.example.sqlexecutorservice.dto

import org.springframework.http.HttpStatus

data class ApiResponse(
    private val status: HttpStatus,
)