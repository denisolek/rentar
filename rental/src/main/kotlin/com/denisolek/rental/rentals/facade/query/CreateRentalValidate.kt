package com.denisolek.rental.rentals.facade.query

import java.time.LocalDateTime
import java.util.*

class CreateRentalValidate(
    val carId: UUID,
    val customerId: UUID,
    val from: LocalDateTime,
    val to: LocalDateTime
)