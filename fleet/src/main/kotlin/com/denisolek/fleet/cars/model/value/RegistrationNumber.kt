package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.facade.CarExceptions.InvalidRegistrationNumberException

class RegistrationNumber private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(value: String): RegistrationNumber {
            when {
                !validate(value) -> throw InvalidRegistrationNumberException()
            }
            return RegistrationNumber(value)
        }

        private fun validate(input: String) = input.length in 4..10
    }
}