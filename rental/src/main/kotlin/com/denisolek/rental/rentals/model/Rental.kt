package com.denisolek.rental.rentals.model

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.infrastructure.isAfterOrEqual
import com.denisolek.rental.infrastructure.isBeforeOrEqual
import com.denisolek.rental.rentals.model.value.RentalId
import com.denisolek.rental.rentals.model.value.RentalPrice
import java.time.LocalDateTime

interface Rental {
    val id: RentalId
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
    val carId: CarId
    val customerId: CustomerId
    val from: LocalDateTime
    val to: LocalDateTime
    val price: RentalPrice

    fun overlaps(start: LocalDateTime, end: LocalDateTime) = (from.isBeforeOrEqual(end)) && (to.isAfterOrEqual(start))
}