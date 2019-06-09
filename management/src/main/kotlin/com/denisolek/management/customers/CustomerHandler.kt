package com.denisolek.management.customers

import com.denisolek.management.customers.domain.CustomerEntity
import com.denisolek.management.customers.domain.CustomerFactory
import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.customers.domain.event.CustomerUpdated
import com.denisolek.management.infrastructure.Globals
import com.denisolek.management.infrastructure.toCustomerEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CustomerHandler(val customerRepository: CustomerRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [Globals.CUSTOMERS_TOPIC])
    fun process(message: String) {
        val event = message.toCustomerEvent()
        when (event) {
            is CustomerAdded -> handle(event)
            is CustomerUpdated -> handle(event)
        }
    }

    fun handle(customerAdded: CustomerAdded) {
        val customer = CustomerFactory.create(customerAdded)
        customerRepository.save(CustomerEntity(customer))
        logger.info("[CustomerAdded] {${customer.id.value}} - Handled")
    }

    fun handle(customerUpdated: CustomerUpdated) {
        val customer = customerRepository.findByIdOrThrow(customerUpdated.aggregateId).toDomainModel()
        customer.apply(customerUpdated)
        customerRepository.save(CustomerEntity(customer))
        logger.info("[CustomerUpdated] {${customer.id.value}} - Handled")
    }
}