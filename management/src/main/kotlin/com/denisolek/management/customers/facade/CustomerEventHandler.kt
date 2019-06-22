package com.denisolek.management.customers.facade

import com.denisolek.management.customers.facade.command.AddCustomerCancelledCommand
import com.denisolek.management.customers.facade.command.UpdateCustomerCancelledCommand
import com.denisolek.management.customers.infrastructure.config.CustomerRepository
import com.denisolek.management.customers.model.CustomerFactory
import com.denisolek.management.customers.model.event.CustomerAdded
import com.denisolek.management.customers.model.event.CustomerUpdated
import com.denisolek.management.infrastructure.Globals
import com.denisolek.management.infrastructure.toCustomerEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CustomerEventHandler(
    val customerRepository: CustomerRepository,
    val commandHandler: CustomerCommandHandler
) {
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
        try {
            val customer = CustomerFactory.create(customerAdded)
            customerRepository.save(customer)
            logger.info("[CustomerAdded] {${customer.id.value}} - Handled")
        } catch (e: Exception) {
            commandHandler.handle(AddCustomerCancelledCommand(customerAdded.aggregateId))
            throw e
        }
    }

    fun handle(customerUpdated: CustomerUpdated) {
        try {
            val customer = customerRepository.findByIdOrThrow(customerUpdated.aggregateId)
            customer.apply(customerUpdated)
            customerRepository.save(customer)
            logger.info("[CustomerUpdated] {${customer.id.value}} - Handled")
        } catch (e: Exception) {
            val customer = customerRepository.findByIdOrThrow(customerUpdated.aggregateId)
            customer.run { commandHandler.handle(UpdateCustomerCancelledCommand(this)) }
            throw e
        }
    }
}