package com.denisolek.management.customers.facade.command

import com.denisolek.management.customers.model.event.CustomerAdded
import com.denisolek.management.customers.model.event.CustomerAddingCancelled
import com.denisolek.management.customers.model.event.CustomerUpdated
import com.denisolek.management.customers.model.event.CustomerUpdatingCancelled
import com.denisolek.management.customers.model.value.CustomerId
import com.denisolek.management.infrastructure.send
import org.apache.kafka.clients.producer.Producer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerCommandHandler(val eventProducer: Producer<String, String>) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun handle(command: AddCustomerCommand): UUID {
        return CustomerAdded(command).run {
            eventProducer.send(this)
            logger.info("[CustomerAdded] {${this.aggregateId}} - Sent")
            this.aggregateId
        }
    }

    fun handle(command: AddCustomerCancelledCommand) {
        CustomerAddingCancelled(command).run {
            eventProducer.send(this)
            logger.info("[CustomerAddingCancelled] {${this.aggregateId}} - Sent")
        }
    }

    fun handle(command: UpdateCustomerCommand, id: UUID) {
        CustomerUpdated(command, CustomerId(id)).run {
            eventProducer.send(this)
            logger.info("[CustomerUpdated] {${this.aggregateId}} - Sent")
        }
    }

    fun handle(command: UpdateCustomerCancelledCommand) {
        CustomerUpdatingCancelled(command).run {
            eventProducer.send(this)
            logger.info("[CustomerUpdatingCancelled] {${this.aggregateId}} - Sent")
        }
    }
}
