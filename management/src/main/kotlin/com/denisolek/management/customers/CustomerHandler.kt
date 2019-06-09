package com.denisolek.management.customers

import com.denisolek.management.customers.domain.CustomerEntity
import com.denisolek.management.customers.domain.CustomerFactory
import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.infrastructure.Globals
import com.denisolek.management.infrastructure.toCustomerEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CustomerHandler(val customerRepository: CustomerRepository) {
    @KafkaListener(topics = [Globals.CUSTOMERS_TOPIC])
    fun process(message: String) {
        val event = message.toCustomerEvent()
        when (event) {
            is CustomerAdded -> handle(event)
        }
    }

    fun handle(customerAdded: CustomerAdded) {
        val customer = CustomerFactory.create(customerAdded)
        customerRepository.save(CustomerEntity(customer))
    }
}