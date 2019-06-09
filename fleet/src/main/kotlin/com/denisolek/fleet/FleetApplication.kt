package com.denisolek.fleet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FleetApplication

fun main(args: Array<String>) {
    runApplication<FleetApplication>(*args)
}
