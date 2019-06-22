package com.denisolek.rental.rentals.model

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.cars.model.value.DailyPrice
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.rentals.model.event.RentalCreated
import com.denisolek.rental.rentals.model.value.RentalId
import com.denisolek.rental.rentals.model.value.RentalPrice

object RentalFactory {
    fun create(e: RentalCreated, dailyPrice: DailyPrice) = UpcomingRental(
        id = RentalId(e.aggregateId),
        carId = CarId(e.carId),
        customerId = CustomerId(e.customerId),
        from = e.from,
        to = e.to,
        price = RentalPrice(dailyPrice, e.from, e.to)
    )
}