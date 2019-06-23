package com.denisolek.rental.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Healthcheck {
    @GetMapping("/rentals/healthcheck")
    fun healthcheck() = "OK"
}