package com.denisolek.customers

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Service
@RestController
class Controller(val kafkaTemplate: KafkaTemplate<String, String>) {
    private val logger = LoggerFactory.getLogger(javaClass)

    var eventsCount = 0

    @PostMapping("/customers")
    fun createCustomer() {
        logger.info("sending $eventsCount to kafka")
        kafkaTemplate.send("events", "customer $eventsCount created")
        eventsCount += 1
    }

    @KafkaListener(topics = ["events"])
    fun getCustomer(message: String) {
        logger.info("Fetched message: $message")
    }
}