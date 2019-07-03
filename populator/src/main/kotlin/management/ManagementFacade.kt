package management

import Setup
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import management.dto.AddCustomerDTO
import management.dto.UpdateCustomerDTO
import management.dto.ValidateAddCustomerDTO
import management.dto.ValidateUpdateCustomerDTO
import java.util.*

class ManagementFacade {
    private val managementClient = Feign.builder()
        .encoder(GsonEncoder())
        .decoder(GsonDecoder())
        .target(ManagementClient::class.java, Setup.apiHost)

    fun getAll(): List<UUID> {
        return managementClient.getAll().map { it.id }
    }

    fun addCustomers(count: Int): List<DummyCustomer> {
        val customers = mutableListOf<DummyCustomer>()
        loop@ for (i in 1..count) {
            Thread.sleep(Setup.requestInterval.toLong())
            val dto = AddCustomerDTO()
            if (validateAddCustomer(dto, i)) continue@loop
            var id: UUID?
            try {
                id = managementClient.addCustomer(dto)
            } catch (ex: Exception) {
                println("Error adding [$i] : ${dto.firstName} ${dto.lastName} !")
                continue@loop
            }
            customers.add(DummyCustomer(id))
            println("Added customer [$i] : ${dto.firstName} ${dto.lastName} {$id}")
        }
        return customers
    }

    private fun validateAddCustomer(dto: AddCustomerDTO, i: Int): Boolean {
        try {
            managementClient.validateAddCustomer(ValidateAddCustomerDTO(dto))
        } catch (ex: Exception) {
            println("Skipping invalid AddCustomer [$i] : ${dto.firstName} ${dto.lastName} !")
            return true
        }
        return false
    }

    fun updateCustomers(customers: List<DummyCustomer>): List<DummyCustomer> {
        val updatedCustomers = mutableListOf<DummyCustomer>()
        loop@ for (i in 1..customers.count() / 2) {
            Thread.sleep(Setup.requestInterval.toLong())
            val dto = UpdateCustomerDTO()
            val id = customers[(1 until customers.count()).random()].id
            if (validateUpdateCustomer(id, dto, i)) continue@loop
            try {
                managementClient.updateCustomer(id, dto)
            } catch (ex: Exception) {
                println("Error updating [$i] {$id} !")
                continue@loop
            }
            updatedCustomers.add(DummyCustomer(id))
            println("Updated customer [$i] : ${dto.firstName} ${dto.lastName} {$id}")
        }
        return updatedCustomers
    }

    private fun validateUpdateCustomer(id: UUID, dto: UpdateCustomerDTO, i: Int): Boolean {
        try {
            managementClient.validateUpdateCustomer(id, ValidateUpdateCustomerDTO(dto))
        } catch (ex: Exception) {
            println("Skipping invalid UpdateCustomer [$i] : ${dto.firstName} ${dto.lastName} !")
            return true
        }
        return false
    }
}