package com.denisolek.rental.cars.model

import com.denisolek.rental.cars.model.event.CarAdded
import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.cars.model.value.CarName
import com.denisolek.rental.cars.model.value.DailyPrice

object CarFactory {
    fun create(e: CarAdded) = Car(
        id = CarId(e.id),
        name = CarName("${e.manufacturer} ${e.model}"),
        dailyPrice = DailyPrice(e.dailyPrice)
    )
}