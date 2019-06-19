package com.denisolek.rental.rentals.facade

import com.denisolek.rental.infrastructure.send
import com.denisolek.rental.rentals.facade.command.CreateRentalCancelledCommand
import com.denisolek.rental.rentals.facade.command.CreateRentalCommand
import com.denisolek.rental.rentals.model.event.RentalCreated
import com.denisolek.rental.rentals.model.event.RentalCreatingCancelled
import org.apache.kafka.clients.producer.Producer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class RentalCommandHandler(val eventProducer: Producer<String, String>) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun handle(command: CreateRentalCommand): UUID {
        return RentalCreated(command).run {
            eventProducer.send(this)
            logger.info("[RentalCreated] {${this.aggregateId}} - Sent")
            this.aggregateId
        }
    }

    fun handle(command: CreateRentalCancelledCommand) {
        RentalCreatingCancelled(command).run {
            eventProducer.send(this)
            logger.info("[CarCreatingCancelled] {${this.aggregateId}} - Sent")
        }
    }
}