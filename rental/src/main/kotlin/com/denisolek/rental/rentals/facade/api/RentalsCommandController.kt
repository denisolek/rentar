package com.denisolek.rental.rentals.facade.api

import com.denisolek.rental.rentals.facade.RentalCommandHandler
import com.denisolek.rental.rentals.facade.command.CreateRentalCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class RentalsCommandController(val commandHandler: RentalCommandHandler) {

    @PostMapping("/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRental(@RequestBody createRentalCommand: CreateRentalCommand): UUID {
        return commandHandler.handle(createRentalCommand)
    }
}