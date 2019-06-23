package com.denisolek.rental.rentals.facade.api

import com.denisolek.rental.rentals.facade.RentalCommandHandler
import com.denisolek.rental.rentals.facade.command.CancelRentalCommand
import com.denisolek.rental.rentals.facade.command.CreateRentalCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RentalCommandController(val commandHandler: RentalCommandHandler) {

    companion object {
        const val RENTAL_ID = "rentalId"
    }

    @PostMapping("/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody createRentalCommand: CreateRentalCommand): UUID {
        return commandHandler.handle(createRentalCommand)
    }

    @PutMapping("/rentals/{$RENTAL_ID}/cancel")
    fun cancel(@PathVariable(required = true, value = RENTAL_ID) id: UUID) {
        commandHandler.handle(CancelRentalCommand(id))
    }
}