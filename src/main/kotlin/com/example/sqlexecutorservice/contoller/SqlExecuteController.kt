package com.example.sqlexecutorservice.contoller

import com.example.sqlexecutorservice.dto.SqlDto
import com.example.sqlexecutorservice.service.SqlExecuteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sql-executor/execute")
class SqlExecuteController(@Autowired val sqlExecuteService: SqlExecuteService) {

    @GetMapping
    fun fetchData(@RequestBody sqlDto: SqlDto): ResponseEntity<Any>{
        return ResponseEntity.ok(sqlExecuteService.executeSelectQuery(sqlDto))
    }
}