package rental.dto

import java.time.LocalDateTime
import java.util.*

class ValidateRentalCreateDTO(
    val carId: UUID,
    val customerId: UUID,
    val from: LocalDateTime,
    val to: LocalDateTime
) {
    constructor(dto: CreateRentalDTO):this(
        carId = dto.carId,
        customerId = dto.customerId,
        from = dto.from,
        to = dto.to
    )
}
