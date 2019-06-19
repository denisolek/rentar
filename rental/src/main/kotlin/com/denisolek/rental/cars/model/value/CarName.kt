package com.denisolek.rental.cars.model.value

import com.denisolek.rental.cars.infrastructure.CarExceptions.InvalidCarName

class CarName private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(name: String): CarName {
            when {
                !validate(name) -> throw InvalidCarName()
            }
            return CarName(name)
        }

        private fun validate(input: String) = input.length in 2..50
    }
}
