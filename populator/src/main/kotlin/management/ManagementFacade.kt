package management

import Setup
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import management.dto.AddCustomerDTO
import java.lang.Exception

class ManagementFacade {
    private val managementClient = Feign.builder()
        .encoder(GsonEncoder())
        .decoder(GsonDecoder())
        .target(ManagementClient::class.java, Setup.managementHost)

    fun addCustomers(count: Int) {
        for (i in 1..count) {
            val dto = AddCustomerDTO()
            try {
                managementClient.addCustomer(dto)
            }catch (ex: Exception) {
                continue
            }
            println("Added customer [$i] : ${dto.firstName} ${dto.lastName}")
            Thread.sleep(Setup.requestInterval.toLong())
        }
    }
}