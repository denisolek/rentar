package com.denisolek.fleet.cars.model

import com.denisolek.fleet.cars.model.event.CarAdded
import com.denisolek.fleet.cars.model.value.*

object CarFactory {
    fun create(e: CarAdded) = Car(
        id = CarId(e.aggregateId),
        manufacturer = Manufacturer(e.manufacturer),
        model = Model(e.model),
        segment = Segment(e.segment),
        productionYear = ProductionYear(e.productionYear),
        mileage = Mileage(e.mileage),
        transmission = Transmission(e.transmission),
        fuel = Fuel(e.fuel),
        registrationNumber = RegistrationNumber(e.registrationNumber)
    )
}