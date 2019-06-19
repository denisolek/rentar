package com.denisolek.rental.rentals.model

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.rentals.model.value.RentalId
import java.time.LocalDateTime

class CompletedRental(
    override val id: RentalId,
    override val carId: CarId,
    override val customerId: CustomerId,
    override val from: LocalDateTime,
    override val to: LocalDateTime
) : Rental {
}