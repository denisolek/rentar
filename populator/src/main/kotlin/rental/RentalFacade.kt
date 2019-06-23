package rental

import GsonConfig
import Setup
import feign.Feign
import feign.gson.GsonEncoder
import rental.dto.CreateRentalDTO
import rental.dto.ValidateRentalCreateDTO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class RentalFacade {
    private val rentalClient = Feign.builder()
        .encoder(GsonConfig.encoder)
        .decoder(GsonConfig.decoder)
        .target(RentalClient::class.java, Setup.rentalHost)

    fun createRentals(count: Int, customers: List<UUID>) {
        val rentals = mutableListOf<DummyRental>()
        loop@ for (i in 1..count) {
            Thread.sleep(Setup.requestInterval.toLong())
            val dates = generateTimeRange()
            val availableCars = rentalClient.estimate(dates.first, dates.second).map { it.carId }
            val dto = CreateRentalDTO(availableCars, customers, dates.first, dates.second)
            if (validateCreateRental(dto, i)) continue@loop
            var id: UUID?
            try {
                id = rentalClient.create(dto)
            } catch (ex: Exception) {
                println("Error creating [$i] : ${dto.carId} ${dto.from} ${dto.to} !")
                continue@loop
            }
            rentals.add(DummyRental(id))
            println("Created rental [$i] : ${dto.carId} ${dto.from} ${dto.to} {$id}")
        }
    }

    private fun generateTimeRange(): Pair<String, String> {
        val from = LocalDateTime.of(
            LocalDate.now().plusDays((-50..200).random().toLong()),
            LocalTime.of((6..23).random(), listOf(0, 15, 30, 45).random())
        )
        val to = LocalDateTime.of(
            from.plusDays((2..24).random().toLong()).toLocalDate(),
            LocalTime.of((6..23).random(), listOf(0, 15, 30, 45).random())
        )
        return from.toString() to to.toString()
    }

    private fun validateCreateRental(dto: CreateRentalDTO, i: Int): Boolean {
        try {
            rentalClient.validateCreate(ValidateRentalCreateDTO(dto))
        } catch (ex: Exception) {
            println("Skipping invalid CreateRental [$i] : ${dto.carId} ${dto.from} ${dto.to} !")
            return true
        }
        return false
    }
}