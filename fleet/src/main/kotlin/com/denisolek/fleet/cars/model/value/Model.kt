package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.facade.CarExceptions.InvalidModelException

class Model private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(name: String): Model {
            when {
                !validate(name) -> throw InvalidModelException()
            }
            return Model(name)
        }

        private fun validate(input: String) = input.length in 2..50
    }
}