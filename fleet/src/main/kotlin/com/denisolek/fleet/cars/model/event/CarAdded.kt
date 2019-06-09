package com.denisolek.fleet.cars.model.event

import com.denisolek.fleet.cars.facade.command.AddCarCommand
import com.denisolek.fleet.cars.model.value.CarId
import com.denisolek.fleet.infrastructure.gson.Gson
import java.time.LocalDateTime
import java.util.*

class CarAdded(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarAdded",
    val manufacturer: String,
    val model: String,
    val segment: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String
) : CarEvent {
    constructor(command: AddCarCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = CarId().value,
        occurredAt = LocalDateTime.now(),
        manufacturer = command.manufacturer,
        model = command.model,
        segment = command.segment,
        productionYear = command.productionYear,
        mileage = command.mileage,
        transmission = command.transmission,
        fuel = command.fuel,
        registrationNumber = command.registrationNumber
    )

    override fun toJson(): String = Gson.create().toJson(this)
}