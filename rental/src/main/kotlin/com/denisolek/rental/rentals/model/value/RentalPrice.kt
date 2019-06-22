package com.denisolek.rental.rentals.model.value

import com.denisolek.rental.cars.model.value.DailyPrice
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class RentalPrice private constructor(
    val value: BigDecimal
) {
    companion object {
        operator fun invoke(dailyPrice: DailyPrice, from: LocalDateTime, to: LocalDateTime): RentalPrice {
            return RentalPrice(dailyPrice.value.multiply(BigDecimal(ChronoUnit.DAYS.between(from, to))))
        }

        fun fromEntity(price: Int) = RentalPrice(price.toBigDecimal())
    }
}
