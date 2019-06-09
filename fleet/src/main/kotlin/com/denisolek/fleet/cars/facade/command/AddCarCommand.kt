package com.denisolek.fleet.cars.facade.command

class AddCarCommand(
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String
)