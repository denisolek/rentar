package com.denisolek.rental.rentals.model

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.rentals.model.value.RentalId
import com.denisolek.rental.rentals.model.value.RentalPrice
import java.time.LocalDateTime

class CancelledRental(
    override val id: RentalId,
    override val createdAt: LocalDateTime,
    override val updatedAt: LocalDateTime,
    override val carId: CarId,
    override val customerId: CustomerId,
    override val from: LocalDateTime,
    override val to: LocalDateTime,
    override val price: RentalPrice
) : Rental {
    constructor(rental: Rental) : this(
        id = rental.id,
        createdAt = rental.createdAt,
        updatedAt = LocalDateTime.now(),
        carId = rental.carId,
        customerId = rental.customerId,
        from = rental.from,
        to = rental.to,
        price = rental.price
    )
}