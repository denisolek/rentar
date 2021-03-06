package com.denisolek.rental.rentals.facade.api

import com.denisolek.rental.rentals.facade.RentalQueryHandler
import com.denisolek.rental.rentals.facade.query.BaseRental
import com.denisolek.rental.rentals.facade.query.CreateRentalValidate
import com.denisolek.rental.rentals.facade.query.DetailedRental
import com.denisolek.rental.rentals.facade.query.RentalEstimate
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
class RentalQueryController(val handler: RentalQueryHandler) {
    companion object {
        const val RENTAL_ID = "rentalId"
        const val CAR_ID = "carId"
        const val CUSTOMER_ID = "customerId"
        const val FROM = "from"
        const val TO = "to"
    }

    @PostMapping("/rentals/validateCreate")
    fun validateCreate(@RequestBody dto: CreateRentalValidate) {
        handler.validateCreate(dto)
    }

    @GetMapping("/rentals")
    fun fetchAll(): List<BaseRental> {
        return handler.fetchAll()
    }

    @GetMapping("/rentals/{$RENTAL_ID}")
    fun fetchOne(@PathVariable(required = true, value = RENTAL_ID) id: UUID): DetailedRental {
        return handler.findOne(id)
    }

    @GetMapping("/rentals/estimate")
    fun estimate(
        @RequestParam(required = true, value = FROM) from: String,
        @RequestParam(required = true, value = TO) to: String
    ): List<RentalEstimate> {
        return handler.estimate(LocalDateTime.parse(from), LocalDateTime.parse(to))
    }

    @GetMapping("/rentals/customers/{$CUSTOMER_ID}")
    fun fetchForCustomer(@PathVariable(required = true, value = CUSTOMER_ID) id: UUID): List<BaseRental> {
        return handler.fetchForCustomer(id)
    }

    @GetMapping("/rentals/cars/{$CAR_ID}")
    fun fetchForCar(@PathVariable(required = true, value = CAR_ID) id: UUID): List<BaseRental> {
        return handler.fetchForCar(id)
    }
}