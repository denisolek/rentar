package com.denisolek.rental.rentals.facade.query

import com.denisolek.rental.rentals.infrastructure.RentalExceptions.DomainMappingException
import com.denisolek.rental.rentals.model.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class DetailedRental(
    val id: UUID,
    val carId: UUID,
    val customerId: UUID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val from: LocalDateTime,
    val to: LocalDateTime,
    val days: Int,
    val price: Int,
    val status: String
) {
    constructor(rental: Rental) : this(
        id = rental.id.value,
        carId = rental.carId.value,
        customerId = rental.customerId.value,
        createdAt = rental.createdAt,
        updatedAt = rental.updatedAt,
        from = rental.from,
        to = rental.to,
        days = ChronoUnit.DAYS.between(rental.from, rental.to).toInt(),
        price = rental.price.value.toInt(),
        status = when (rental) {
            is CancelledRental -> "Cancelled"
            is CompletedRental -> "Completed"
            is OngoingRental -> "Ongoing"
            is UpcomingRental -> "Upcoming"
            else -> throw DomainMappingException()
        }
    )
}