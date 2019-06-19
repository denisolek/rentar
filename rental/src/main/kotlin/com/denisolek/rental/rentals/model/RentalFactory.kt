package com.denisolek.rental.rentals.model

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.rentals.model.event.RentalCreated
import com.denisolek.rental.rentals.model.value.RentalId

object RentalFactory {
    fun create(e: RentalCreated) = UpcomingRental(
        id = RentalId(e.aggregateId),
        carId = CarId(e.carId),
        customerId = CustomerId(e.customerId),
        from = e.from,
        to = e.to
    )
}