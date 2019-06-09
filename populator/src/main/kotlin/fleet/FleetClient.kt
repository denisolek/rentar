package fleet

import feign.Headers
import feign.Param
import feign.RequestLine
import fleet.dto.AddCarDTO
import fleet.dto.UpdateCarDTO
import fleet.dto.ValidateAddCarDTO
import fleet.dto.ValidateUpdateCarDTO
import management.dto.UpdateCustomerDTO
import java.util.*

interface FleetClient {
    /**
     * Add customer
     */
    @RequestLine("POST cars/validateAdd")
    @Headers("Content-Type: application/json")
    fun validateAddCar(validateAddCarDTO: ValidateAddCarDTO)

    @RequestLine("POST /cars")
    @Headers("Content-Type: application/json")
    fun addCar(addCarDTO: AddCarDTO): UUID

    /**
     * Update customer
     */
    @RequestLine("POST /cars/{carId}/validateUpdate")
    @Headers("Content-Type: application/json")
    fun validateUpdateCar(
        @Param("carId") carId: UUID,
        validateUpdateCarDTO: ValidateUpdateCarDTO
    )

    @RequestLine("PUT /cars/{carId}")
    @Headers("Content-Type: application/json")
    fun updateCar(
        @Param("carId") carId: UUID,
        updateCarDTO: UpdateCarDTO
    )
}