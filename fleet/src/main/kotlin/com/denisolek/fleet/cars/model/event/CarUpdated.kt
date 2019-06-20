package com.denisolek.fleet.cars.model.event

import com.denisolek.fleet.cars.facade.command.UpdateCarCommand
import com.denisolek.fleet.cars.model.value.CarId
import java.time.LocalDateTime
import java.util.*

class CarUpdated(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarUpdated",
    val manufacturer: String,
    val model: String,
    val segment: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String,
    val dailyPrice: Int
) : CarEvent {
    constructor(command: UpdateCarCommand, id: CarId) : this(
        id = UUID.randomUUID(),
        aggregateId = id.value,
        occurredAt = LocalDateTime.now(),
        manufacturer = command.manufacturer,
        model = command.model,
        segment = command.segment,
        productionYear = command.productionYear,
        mileage = command.mileage,
        transmission = command.transmission,
        fuel = command.fuel,
        registrationNumber = command.registrationNumber,
        dailyPrice = command.dailyPrice
    )
}
