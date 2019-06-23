package rental.dto

import java.time.LocalDateTime
import java.util.*

class CreateRentalDTO private constructor(
    val customerId: UUID,
    val carId: UUID,
    val from: LocalDateTime,
    val to: LocalDateTime
) {
    companion object {
        operator fun invoke(cars: List<UUID>, customers: List<UUID>, from: String, to: String): CreateRentalDTO {
            val customerId = customers.random()
            val carId = cars.random()

            return CreateRentalDTO(customerId, carId, LocalDateTime.parse(from), LocalDateTime.parse(to))
        }
    }
}