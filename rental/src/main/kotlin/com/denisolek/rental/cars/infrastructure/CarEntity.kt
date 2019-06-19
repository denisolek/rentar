package com.denisolek.rental.cars.infrastructure

import com.denisolek.rental.cars.model.Car
import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.cars.model.value.CarName
import com.denisolek.rental.cars.model.value.DailyPrice
import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CarEntity(@Id val id: UUID, val name: String, val dailyPrice: BigDecimal) {
    constructor(car: Car) : this(
        id = car.id.value,
        name = car.name.value,
        dailyPrice = car.dailyPrice.value
    )

    fun toDomainModel() = Car(
        id = CarId(this.id),
        name = CarName(this.name),
        dailyPrice = DailyPrice(this.dailyPrice)
    )
}