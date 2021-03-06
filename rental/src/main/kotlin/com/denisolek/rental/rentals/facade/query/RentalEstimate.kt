package com.denisolek.rental.rentals.facade.query

import java.time.LocalDateTime
import java.util.*

class RentalEstimate(
    val carId: UUID,
    val carName: String,
    val price: Int,
    val from: LocalDateTime,
    val to: LocalDateTime
)