package com.denisolek.fleet.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Healthcheck {
    @GetMapping("/cars/healthcheck")
    fun healthcheck() = "OK"
}