package fleet

import Setup
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import fleet.dto.AddCarDTO
import fleet.dto.UpdateCarDTO
import fleet.dto.ValidateAddCarDTO
import fleet.dto.ValidateUpdateCarDTO
import java.util.*

class FleetFacade {
    private val fleetClient = Feign.builder()
        .encoder(GsonEncoder())
        .decoder(GsonDecoder())
        .target(FleetClient::class.java, Setup.apiHost)

    fun addCars(count: Int): List<DummyCar> {
        val cars = mutableListOf<DummyCar>()
        loop@ for (i in 1..count) {
            Thread.sleep(Setup.requestInterval.toLong())
            val dto = AddCarDTO()
            if (validateAddCar(dto, i)) continue@loop
            var id: UUID?
            try {
                id = fleetClient.addCar(dto)
            } catch (ex: Exception) {
                println("Error adding [$i] : ${dto.manufacturer} ${dto.model} ${dto.registrationNumber} !")
                continue@loop
            }
            cars.add(DummyCar(id))
            println("Added car [$i] : ${dto.manufacturer} ${dto.model} ${dto.registrationNumber} {$id}")
        }
        return cars
    }

    private fun validateAddCar(dto: AddCarDTO, i: Int): Boolean {
        try {
            fleetClient.validateAddCar(ValidateAddCarDTO(dto))
        } catch (ex: Exception) {
            println("Skipping invalid AddCar [$i] : ${dto.manufacturer} ${dto.model} ${dto.registrationNumber} !")
            return true
        }
        return false
    }

    fun updateCars(cars: List<DummyCar>): List<DummyCar> {
        val updatedCars = mutableListOf<DummyCar>()
        loop@ for (i in 1..cars.count() / 2) {
            Thread.sleep(Setup.requestInterval.toLong())
            val dto = UpdateCarDTO()
            val id = cars[(1 until cars.count()).random()].id
            if (validateUpdateCar(id, dto, i)) continue@loop
            try {
                fleetClient.updateCar(id, dto)
            } catch (ex: Exception) {
                println("Error updating [$i] {$id} !")
                continue@loop
            }
            updatedCars.add(DummyCar(id))
            println("Updated car [$i] : ${dto.manufacturer} ${dto.model} ${dto.registrationNumber} {$id}")
        }
        return updatedCars
    }

    private fun validateUpdateCar(id: UUID, dto: UpdateCarDTO, i: Int): Boolean {
        try {
            fleetClient.validateUpdateCar(id, ValidateUpdateCarDTO(dto))
        } catch (ex: Exception) {
            println("Skipping invalid UpdateCar [$i] : ${dto.manufacturer} ${dto.model} ${dto.registrationNumber} !")
            return true
        }
        return false
    }
}