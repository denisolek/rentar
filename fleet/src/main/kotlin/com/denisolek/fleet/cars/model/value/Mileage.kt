package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.facade.CarExceptions.InvalidMileageException

class Mileage private constructor(
    val value: Int
) {
    companion object {
        operator fun invoke(value: Int): Mileage {
            when {
                !validate(value) -> throw InvalidMileageException()
            }
            return Mileage(value)
        }

        private fun validate(input: Int) = input in 1..9999999
    }
}