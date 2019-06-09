package com.denisolek.fleet.cars.model

import com.denisolek.fleet.cars.model.event.CarUpdated
import com.denisolek.fleet.cars.model.value.*

class Car(
    val id: CarId,
    var manufacturer: Manufacturer,
    var model: Model,
    var productionYear: ProductionYear,
    var mileage: Mileage,
    var transmission: Transmission,
    var fuel: Fuel,
    var registrationNumber: RegistrationNumber
) {
    fun apply(e: CarUpdated) {
        this.manufacturer = Manufacturer(e.manufacturer)
        this.model = Model(e.model)
        this.productionYear = ProductionYear(e.productionYear)
        this.mileage = Mileage(e.mileage)
        this.transmission = Transmission(e.transmission)
        this.fuel = Fuel(e.fuel)
        this.registrationNumber = RegistrationNumber(e.registrationNumber)
    }
}