package rental

import feign.Headers
import feign.Param
import feign.RequestLine
import rental.dto.CreateRentalDTO
import rental.dto.RentalEstimate
import rental.dto.ValidateRentalCreateDTO
import java.util.*

interface RentalClient {
    companion object {
        const val RENTAL_ID = "rentalId"
        const val FROM = "from"
        const val TO = "to"
    }

    /**
     * Create rental
     */
    @RequestLine("POST /rentals/validateCreate")
    @Headers("Content-Type: application/json")
    fun validateCreate(validateRentalCreateDTO: ValidateRentalCreateDTO)

    @RequestLine("GET /rentals/estimate?from={$FROM}&to={$TO}")
    @Headers("Content-Type: application/json")
    fun estimate(@Param(value = FROM) from: String, @Param(value = TO) to: String): List<RentalEstimate>

    @RequestLine("POST /rentals")
    @Headers("Content-Type: application/json")
    fun create(createRentalDTO: CreateRentalDTO): UUID

    /**
     * Cancel rental
     */
    @RequestLine("PUT /rentals/{$RENTAL_ID}/cancel")
    @Headers("Content-Type: application/json")
    fun cancel(@Param(value = RENTAL_ID) id: UUID)
}