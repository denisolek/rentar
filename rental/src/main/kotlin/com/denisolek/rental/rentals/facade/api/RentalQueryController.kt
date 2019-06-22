package com.denisolek.rental.rentals.facade.api

import com.denisolek.rental.rentals.facade.RentalQueryHandler
import com.denisolek.rental.rentals.facade.query.BaseRental
import com.denisolek.rental.rentals.facade.query.CreateRentalValidate
import com.denisolek.rental.rentals.facade.query.DetailedRental
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class RentalQueryController(val handler: RentalQueryHandler) {
    companion object {
        const val RENTAL_ID = "rentalId"
    }

    @PostMapping("/rentals/validateCreate")
    fun validateCreate(@RequestBody dto: CreateRentalValidate) {
        handler.validateCreate(dto)
    }

    @GetMapping("/rentals")
    fun fetchAll(): List<BaseRental> {
        return handler.fetchAll()
    }

    @GetMapping("/rentals{$RENTAL_ID}")
    fun fetchOne(@PathVariable(required = true, value = RENTAL_ID) id: UUID): DetailedRental {
        return handler.findOne(id)
    }
}