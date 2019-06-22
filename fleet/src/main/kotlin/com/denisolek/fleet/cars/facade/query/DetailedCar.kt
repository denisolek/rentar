package com.denisolek.fleet.cars.facade.query

import com.denisolek.fleet.cars.model.Car
import java.time.LocalDateTime
import java.util.*

class DetailedCar(
    val id: UUID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val manufacturer: String,
    val model: String,
    val segment: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String,
    val dailyPrice: Int
) {
    constructor(car: Car) : this(
        id = car.id.value,
        createdAt = car.createdAt,
        updatedAt = car.updatedAt,
        manufacturer = car.manufacturer.value,
        model = car.model.value,
        segment = car.segment.type.name,
        productionYear = car.productionYear.value,
        mileage = car.mileage.value,
        transmission = car.transmission.type.name,
        fuel = car.fuel.type.name,
        registrationNumber = car.registrationNumber.value,
        dailyPrice = car.dailyPrice.value.toInt()
    )
}
