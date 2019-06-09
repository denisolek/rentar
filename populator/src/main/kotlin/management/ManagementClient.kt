package management

import feign.Headers
import feign.RequestLine
import management.dto.AddCustomerDTO

interface ManagementClient {
    @RequestLine("POST /customers")
    @Headers("Content-Type: application/json")
    fun addCustomer(addCustomerDTO: AddCustomerDTO)
}