package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.infrastructure.CarExceptions.InvalidProductionYearException
import java.time.LocalDate

class ProductionYear private constructor(
    val value: Int
) {
    companion object {
        operator fun invoke(value: Int): ProductionYear {
            when {
                !validate(value) -> throw InvalidProductionYearException()
            }
            return ProductionYear(value)
        }

        private fun validate(input: Int) = input in 1700..LocalDate.now().year
    }
}