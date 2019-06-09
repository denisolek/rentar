package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.facade.CarExceptions.InvalidManufacturerException

class Manufacturer private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(name: String): Manufacturer {
            when {
                !validate(name) -> throw InvalidManufacturerException()
            }
            return Manufacturer(name)
        }

        private fun validate(input: String) = input.length in 2..50
    }
}