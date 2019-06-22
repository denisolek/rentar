package com.denisolek.fleet.cars.infrastructure

import com.denisolek.fleet.cars.model.Car
import com.denisolek.fleet.cars.model.value.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "car")
class CarEntity(
    @Id
    val id: UUID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val manufacturer: String,
    val model: String,
    val segment: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    @Column(unique = true)
    val registrationNumber: String,
    val dailyPrice: BigDecimal
) {
    constructor(car: Car) : this(
        id = car.id.value,
        createdAt = car.createdAt,
        updatedAt = car.updatedAt,
        manufacturer = car.manufacturer.value,
        model = car.model.value,
        segment = car.segment.type.name,
        productionYear = car.productionYear.value,
        mileage = car.mileage.value,
        transmission = car.transmission.type.name,
        fuel = car.fuel.type.name,
        registrationNumber = car.registrationNumber.value,
        dailyPrice = car.dailyPrice.value
    )

    fun toDomainModel() = Car(
        id = CarId(this.id),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        manufacturer = Manufacturer(this.manufacturer),
        model = Model(this.model),
        segment = Segment(this.segment),
        productionYear = ProductionYear(this.productionYear),
        mileage = Mileage(this.mileage),
        transmission = Transmission(this.transmission),
        fuel = Fuel(this.fuel),
        registrationNumber = RegistrationNumber(this.registrationNumber),
        dailyPrice = DailyPrice(this.dailyPrice)
    )
}