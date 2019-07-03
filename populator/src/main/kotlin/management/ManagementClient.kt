package management

import feign.Headers
import feign.Param
import feign.RequestLine
import management.dto.*
import java.util.*

interface ManagementClient {
    /**
     * Get all customers
     */
    @RequestLine("GET /customers")
    @Headers("Content-Type: application/json")
    fun getAll(): List<BaseCustomer>

    /**
     * Add customer
     */
    @RequestLine("POST customers/validateAdd")
    @Headers("Content-Type: application/json")
    fun validateAddCustomer(validateAddCustomerDTO: ValidateAddCustomerDTO)

    @RequestLine("POST /customers")
    @Headers("Content-Type: application/json")
    fun addCustomer(addCustomerDTO: AddCustomerDTO): UUID

    /**
     * Update customer
     */
    @RequestLine("POST /customers/{customerId}/validateUpdate")
    @Headers("Content-Type: application/json")
    fun validateUpdateCustomer(
        @Param("customerId") customerId: UUID,
        validateUpdateCustomerDTO: ValidateUpdateCustomerDTO
    )

    @RequestLine("PUT /customers/{customerId}")
    @Headers("Content-Type: application/json")
    fun updateCustomer(
        @Param("customerId") customerId: UUID,
        updateCustomerDTO: UpdateCustomerDTO
    )
}