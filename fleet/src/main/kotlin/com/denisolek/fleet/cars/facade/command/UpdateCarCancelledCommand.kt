package com.denisolek.fleet.cars.facade.command

import com.denisolek.fleet.cars.model.Car
import java.util.*

class UpdateCarCancelledCommand(
    val aggregateId: UUID,
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String
) {
    constructor(car: Car) : this(
        aggregateId = car.id.value,
        manufacturer = car.manufacturer.value,
        model = car.model.value,
        productionYear = car.productionYear.value,
        mileage = car.mileage.value,
        transmission = car.transmission.type.name,
        fuel = car.fuel.type.name,
        registrationNumber = car.registrationNumber.value
    )
}