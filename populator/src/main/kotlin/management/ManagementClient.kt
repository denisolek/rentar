package management

import feign.Headers
import feign.Param
import feign.RequestLine
import management.dto.AddCustomerDTO
import management.dto.UpdateCustomerDTO
import java.util.*

interface ManagementClient {
    @RequestLine("POST /customers")
    @Headers("Content-Type: application/json")
    fun addCustomer(addCustomerDTO: AddCustomerDTO): UUID

    @RequestLine("PUT /customers/{customerId}")
    @Headers("Content-Type: application/json")
    fun updateCustomer(
        @Param("customerId") customerId: UUID,
        updateCustomerDTO: UpdateCustomerDTO
    )
}