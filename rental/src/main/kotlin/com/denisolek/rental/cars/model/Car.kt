package com.denisolek.rental.cars.model

import com.denisolek.rental.cars.model.event.CarUpdated
import com.denisolek.rental.cars.model.event.CarUpdatingCancelled
import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.cars.model.value.CarName
import com.denisolek.rental.cars.model.value.DailyPrice

class Car(
    val id: CarId,
    var name: CarName,
    var dailyPrice: DailyPrice
) {
    fun apply(e: CarUpdated) {
        this.name = CarName("${e.manufacturer} ${e.model}")
        this.dailyPrice = DailyPrice(e.dailyPrice)
    }

    fun apply(e: CarUpdatingCancelled) {
        this.name = CarName("${e.manufacturer} ${e.model}")
        this.dailyPrice = DailyPrice(e.dailyPrice)
    }
}