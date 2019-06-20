package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.infrastructure.CarExceptions
import java.math.BigDecimal

class DailyPrice private constructor(
    val value: BigDecimal
) {
    companion object {
        operator fun invoke(price: Int): DailyPrice {
            when {
                !validate(price) -> throw CarExceptions.InvalidDailyPrice()
            }
            return DailyPrice(price.toBigDecimal())
        }

        operator fun invoke(price: BigDecimal): DailyPrice {
            when {
                !validate(price) -> throw CarExceptions.InvalidDailyPrice()
            }
            return DailyPrice(price)
        }

        private fun validate(price: Int) = price > 0
        private fun validate(price: BigDecimal) = price > BigDecimal.ZERO
    }
}
