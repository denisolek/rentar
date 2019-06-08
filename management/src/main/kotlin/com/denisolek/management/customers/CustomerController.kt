package com.denisolek.management.customers

import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.customers.dto.AddCustomerDTO
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(val kafkaTemplate: KafkaTemplate<String, String>) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCustomer(@RequestBody addCustomerDTO: AddCustomerDTO) {
        addCustomerDTO.validate()
        kafkaTemplate.send("events", "Customer", CustomerAdded(addCustomerDTO).toJson())
    }
}