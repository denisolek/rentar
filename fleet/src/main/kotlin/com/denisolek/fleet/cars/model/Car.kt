package com.denisolek.fleet.cars.model

import com.denisolek.fleet.cars.model.value.*

class Car(
    val id: CarId,
    val manufacturer: Manufacturer,
    val model: Model,
    val productionYear: ProductionYear,
    val mileage: Mileage,
    val transmission: Transmission,
    val fuel: Fuel,
    val registrationNumber: RegistrationNumber
)