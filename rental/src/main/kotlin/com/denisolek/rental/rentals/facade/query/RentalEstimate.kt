package com.denisolek.rental.rentals.facade.query

import java.time.LocalDateTime

class RentalEstimate(
    val carName: String,
    val price: Int,
    val from: LocalDateTime,
    val to: LocalDateTime
)