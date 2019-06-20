package com.denisolek.rental.customers.facade

import com.denisolek.rental.customers.infrastructure.CustomerRepository
import com.denisolek.rental.customers.model.CustomerFactory
import com.denisolek.rental.customers.model.event.CustomerAdded
import com.denisolek.rental.customers.model.event.CustomerAddingCancelled
import com.denisolek.rental.infrastructure.Globals
import com.denisolek.rental.infrastructure.toCustomerEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CustomerEventHandler(val repository: CustomerRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [Globals.CUSTOMERS_TOPIC])
    fun process(message: String) {
        val event = message.toCustomerEvent()
        when (event) {
            is CustomerAdded -> handle(event)
            is CustomerAddingCancelled -> handle(event)
        }
    }

    fun handle(e: CustomerAdded) {
        CustomerFactory.create(e).run {
            repository.save(this)
            logger.info("[CustomerAdded] {${this.id.value}} - Handled")
        }
    }

    fun handle(e: CustomerAddingCancelled) {
        repository.deleteOrThrow(e.aggregateId)
        logger.info("[CustomerAddingCancelled] {${e.aggregateId}} - Handled")
    }
}