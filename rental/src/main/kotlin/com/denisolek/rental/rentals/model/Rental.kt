package com.denisolek.rental.rentals.model

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.rentals.model.value.RentalId
import java.time.LocalDateTime

interface Rental {
    val id: RentalId
    val carId: CarId
    val customerId: CustomerId
    val from: LocalDateTime
    val to: LocalDateTime

    fun overlaps(start: LocalDateTime, end: LocalDateTime) = from < start && to > end
}