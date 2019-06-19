package com.denisolek.rental.rentals.facade.command

import java.time.LocalDateTime
import java.util.*

class CreateRentalCommand(
    val customerId: UUID,
    val carId: UUID,
    val from: LocalDateTime,
    val to: LocalDateTime
)