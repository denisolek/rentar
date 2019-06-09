package management

import Setup
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import management.dto.AddCustomerDTO

class ManagementFacade {
    private val managementClient = Feign.builder()
        .encoder(GsonEncoder())
        .decoder(GsonDecoder())
        .target(ManagementClient::class.java, Setup.managementHost)

    fun addCustomers(count: Int) {
        for (i in 1..count) {
            val dto = AddCustomerDTO()
            managementClient.addCustomer(dto)
            println("Added customer [$i] : ${dto.firstName} ${dto.lastName}")
        }
    }
}