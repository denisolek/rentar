package com.denisolek.rental.rentals.facade.api

import com.denisolek.rental.rentals.facade.RentalQueryHandler
import com.denisolek.rental.rentals.facade.query.BaseRental
import com.denisolek.rental.rentals.facade.query.CreateRentalValidate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RentalQueryController(val handler: RentalQueryHandler) {
    @PostMapping("/rentals/validateCreate")
    fun validateCreate(@RequestBody dto: CreateRentalValidate) {
        handler.validateCreate(dto)
    }

    @GetMapping("/rentals")
    fun fetchAll(): List<BaseRental> {
        return handler.fetchAll()
    }
}