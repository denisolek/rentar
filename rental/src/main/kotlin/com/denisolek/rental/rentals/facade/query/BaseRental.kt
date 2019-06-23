package com.denisolek.rental.rentals.facade.query

import com.denisolek.rental.rentals.infrastructure.RentalExceptions.DomainMappingException
import com.denisolek.rental.rentals.model.*
import java.time.LocalDateTime
import java.util.*

class BaseRental(
    val id: UUID,
    val from: LocalDateTime,
    val to: LocalDateTime,
    val status: String,
    val price: Int
) {
    constructor(rental: Rental) : this(
        id = rental.id.value,
        from = rental.from,
        to = rental.to,
        status = when (rental) {
            is CancelledRental -> "Cancelled"
            is CompletedRental -> "Completed"
            is OngoingRental -> "Ongoing"
            is UpcomingRental -> "Upcoming"
            else -> throw DomainMappingException()
        },
        price = rental.price.value.toInt()
    )
}