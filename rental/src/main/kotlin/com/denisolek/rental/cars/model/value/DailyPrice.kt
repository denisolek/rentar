package com.denisolek.rental.cars.model.value

import java.math.BigDecimal

class DailyPrice private constructor(
    val value: BigDecimal
) {
    companion object {
        operator fun invoke(price: Int): DailyPrice {
            return DailyPrice(price.toBigDecimal())
        }

        operator fun invoke(price: BigDecimal): DailyPrice {
            return DailyPrice(price)
        }
    }
}
