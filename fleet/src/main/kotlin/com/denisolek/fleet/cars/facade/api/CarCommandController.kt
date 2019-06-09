package com.denisolek.fleet.cars.facade.api

import com.denisolek.fleet.cars.facade.command.AddCarCommand
import com.denisolek.fleet.cars.facade.CarCommandHandler
import com.denisolek.fleet.cars.facade.command.UpdateCarCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CarCommandController(val commandHandler: CarCommandHandler) {
    companion object {
        const val CAR_ID = "customerId"
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCar(@RequestBody addCarCommand: AddCarCommand): UUID {
        return commandHandler.handle(addCarCommand)
    }

    @PutMapping("/cars/{$CAR_ID}")
    fun updateCustomer(
        @PathVariable(required = true, value = CAR_ID) id: UUID,
        @RequestBody updateCarCommand: UpdateCarCommand
    ) {
        commandHandler.handle(updateCarCommand, id)
    }
}