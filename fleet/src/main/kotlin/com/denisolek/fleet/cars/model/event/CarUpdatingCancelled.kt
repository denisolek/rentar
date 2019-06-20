package com.denisolek.fleet.cars.model.event

import com.denisolek.fleet.cars.facade.command.UpdateCarCancelledCommand
import java.time.LocalDateTime
import java.util.*

class CarUpdatingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarUpdatingCancelled",
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String
) : CarEvent {
    constructor(command: UpdateCarCancelledCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = command.aggregateId,
        occurredAt = LocalDateTime.now(),
        manufacturer = command.manufacturer,
        model = command.model,
        productionYear = command.productionYear,
        mileage = command.mileage,
        transmission = command.transmission,
        fuel = command.fuel,
        registrationNumber = command.registrationNumber
    )
}
