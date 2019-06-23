package com.denisolek.management.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Healthcheck {
    @GetMapping("/customers/healthcheck")
    fun healthcheck() = "OK"
}