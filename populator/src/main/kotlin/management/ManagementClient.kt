package management

import feign.Headers
import feign.Param
import feign.RequestLine
import management.dto.AddCustomerDTO
import management.dto.AddValidateDTO
import management.dto.UpdateCustomerDTO
import management.dto.UpdateValidateDTO
import java.util.*

interface ManagementClient {
    /**
     * Add customer
     */
    @RequestLine("POST customers/validateAdd")
    @Headers("Content-Type: application/json")
    fun validateAdd(addValidateDTO: AddValidateDTO)

    @RequestLine("POST /customers")
    @Headers("Content-Type: application/json")
    fun addCustomer(addCustomerDTO: AddCustomerDTO): UUID

    /**
     * Update customer
     */
    @RequestLine("POST /customers/{customerId}/validateUpdate")
    @Headers("Content-Type: application/json")
    fun validateUpdate(
        @Param("customerId") customerId: UUID,
        updateValidateDTO: UpdateValidateDTO
    )

    @RequestLine("PUT /customers/{customerId}")
    @Headers("Content-Type: application/json")
    fun updateCustomer(
        @Param("customerId") customerId: UUID,
        updateCustomerDTO: UpdateCustomerDTO
    )
}