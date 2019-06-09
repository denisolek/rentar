package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.infrastructure.CarExceptions.InvalidFuelException

class Fuel private constructor(
    val type: FuelType
) {
    enum class FuelType {
        PETROL,
        DIESEL,
        ELECTRICITY
    }

    companion object {
        operator fun invoke(value: String): Fuel {
            val type = when (value.toUpperCase()) {
                "PETROL" -> FuelType.PETROL
                "DIESEL" -> FuelType.DIESEL
                "ELECTRICITY" -> FuelType.ELECTRICITY
                else -> throw InvalidFuelException()
            }
            return Fuel(type)
        }
    }
}