package com.denisolek.fleet.cars.model

import com.denisolek.fleet.cars.model.event.CarUpdated
import com.denisolek.fleet.cars.model.value.*
import java.time.LocalDateTime

class Car(
    val id: CarId,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var manufacturer: Manufacturer,
    var model: Model,
    var segment: Segment,
    var productionYear: ProductionYear,
    var mileage: Mileage,
    var transmission: Transmission,
    var fuel: Fuel,
    var registrationNumber: RegistrationNumber,
    var dailyPrice: DailyPrice
) {
    fun apply(e: CarUpdated) {
        this.updatedAt = e.occurredAt
        this.manufacturer = Manufacturer(e.manufacturer)
        this.model = Model(e.model)
        this.segment = Segment(e.segment)
        this.productionYear = ProductionYear(e.productionYear)
        this.mileage = Mileage(e.mileage)
        this.transmission = Transmission(e.transmission)
        this.fuel = Fuel(e.fuel)
        this.registrationNumber = RegistrationNumber(e.registrationNumber)
        this.dailyPrice = DailyPrice(e.dailyPrice)
    }
}