package com.denisolek.customers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
class CustomersApplication

fun main(args: Array<String>) {
	runApplication<CustomersApplication>(*args)
}
