package com.denisolek.fleet.cars.facade

import com.denisolek.fleet.cars.facade.command.AddCarCancelledCommand
import com.denisolek.fleet.cars.facade.command.AddCarCommand
import com.denisolek.fleet.cars.facade.command.UpdateCarCancelledCommand
import com.denisolek.fleet.cars.facade.command.UpdateCarCommand
import com.denisolek.fleet.cars.model.event.CarAdded
import com.denisolek.fleet.cars.model.event.CarAddingCancelled
import com.denisolek.fleet.cars.model.event.CarUpdated
import com.denisolek.fleet.cars.model.event.CarUpdatingCancelled
import com.denisolek.fleet.cars.model.value.CarId
import com.denisolek.fleet.infrastructure.send
import org.apache.kafka.clients.producer.Producer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarCommandHandler(val eventProducer: Producer<String, String>) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun handle(command: AddCarCommand): UUID {
        return CarAdded(command).run {
            eventProducer.send(this)
            logger.info("[CarAdded] {${this.aggregateId}} - Sent")
            this.aggregateId
        }
    }

    fun handle(command: AddCarCancelledCommand) {
        CarAddingCancelled(command).run {
            eventProducer.send(this)
            logger.info("[CarAddingCancelled] {${this.aggregateId}} - Sent")
        }
    }

    fun handle(command: UpdateCarCommand, id: UUID) {
        CarUpdated(command, CarId(id)).run {
            eventProducer.send(this)
            logger.info("[CarUpdated] {${this.aggregateId}} - Sent")
        }
    }

    fun handle(command: UpdateCarCancelledCommand) {
        CarUpdatingCancelled(command).run {
            eventProducer.send(this)
            logger.info("[CarUpdatingCancelled] {${this.aggregateId}} - Sent")
        }
    }
}
