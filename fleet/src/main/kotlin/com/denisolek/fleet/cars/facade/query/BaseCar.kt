package com.denisolek.fleet.cars.facade.query

import com.denisolek.fleet.cars.model.Car
import java.util.*

class BaseCar(
    val id: UUID,
    val registrationNumber: String,
    val manufacturer: String,
    val model: String,
    val productionYear: Int
) {
    constructor(car: Car) : this(
        id = car.id.value,
        registrationNumber = car.registrationNumber.value,
        manufacturer = car.manufacturer.value,
        model = car.model.value,
        productionYear = car.productionYear.value
    )
}
