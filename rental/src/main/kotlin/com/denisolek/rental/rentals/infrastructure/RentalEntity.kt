package com.denisolek.rental.rentals.infrastructure

import com.denisolek.rental.cars.model.value.CarId
import com.denisolek.rental.customers.model.value.CustomerId
import com.denisolek.rental.infrastructure.isBetweenInclusive
import com.denisolek.rental.rentals.infrastructure.RentalExceptions.DomainMappingException
import com.denisolek.rental.rentals.model.*
import com.denisolek.rental.rentals.model.value.RentalId
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "rental")
class RentalEntity(
    @Id
    val id: UUID,
    val carId: UUID,
    val customerId: UUID,
    @Column(name = "start")
    val from: LocalDateTime,
    @Column(name = "end")
    val to: LocalDateTime,
    val cancelled: Boolean
) {
    constructor(rental: Rental) : this(
        id = rental.id.value,
        carId = rental.carId.value,
        customerId = rental.customerId.value,
        from = rental.from,
        to = rental.to,
        cancelled = rental is CancelledRental
    )

    fun toDomainModel(): Rental = when {
        cancelled -> cancelledRental()
        isUpcoming() -> upcomingRental()
        isOngoing() -> ongoingRental()
        isCompleted() -> completedRental()
        else -> throw DomainMappingException()
    }

    private fun upcomingRental(): UpcomingRental {
        return UpcomingRental(
            id = RentalId(this.id),
            carId = CarId(this.carId),
            customerId = CustomerId(this.customerId),
            from = this.from,
            to = this.to
        )
    }

    private fun isUpcoming() = from.isAfter(LocalDateTime.now())

    private fun cancelledRental(): CancelledRental {
        return CancelledRental(
            id = RentalId(this.id),
            carId = CarId(this.carId),
            customerId = CustomerId(this.customerId),
            from = this.from,
            to = this.to
        )
    }

    private fun isOngoing() = LocalDateTime.now().isBetweenInclusive(from, to)

    private fun ongoingRental(): OngoingRental {
        return OngoingRental(
            id = RentalId(this.id),
            carId = CarId(this.carId),
            customerId = CustomerId(this.customerId),
            from = this.from,
            to = this.to
        )
    }

    private fun isCompleted() = LocalDateTime.now().isAfter(to)

    private fun completedRental(): CompletedRental {
        return CompletedRental(
            id = RentalId(this.id),
            carId = CarId(this.carId),
            customerId = CustomerId(this.customerId),
            from = this.from,
            to = this.to
        )
    }
}