package com.denisolek.fleet.cars.facade.api

import com.denisolek.fleet.cars.facade.CarQueryHandler
import com.denisolek.fleet.cars.facade.query.AddCarValidate
import com.denisolek.fleet.cars.facade.query.BaseCar
import com.denisolek.fleet.cars.facade.query.DetailedCar
import com.denisolek.fleet.cars.facade.query.UpdateCarValidate
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CarQueryController(val queryHandler: CarQueryHandler) {
    companion object {
        const val CAR_ID = "carId"
    }

    @PostMapping("/cars/validateAdd")
    fun validateAdd(@RequestBody dto: AddCarValidate) {
        queryHandler.validateAdd(dto)
    }

    @PostMapping("/cars/{$CAR_ID}/validateUpdate")
    fun validateUpdate(
        @PathVariable(required = true, value = CAR_ID) id: UUID,
        @RequestBody dto: UpdateCarValidate
    ) {
        queryHandler.validateUpdate(dto, id)
    }

    @GetMapping("/cars")
    fun fetchAll(): List<BaseCar> {
        return queryHandler.fetchAll()
    }

    @GetMapping("/cars/{$CAR_ID}")
    fun fetchOne(@PathVariable(required = true, value = CAR_ID) id: UUID): DetailedCar {
        return queryHandler.fetchOne(id)
    }
}