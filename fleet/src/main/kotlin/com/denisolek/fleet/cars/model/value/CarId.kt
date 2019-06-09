package com.denisolek.fleet.cars.model.value

import java.io.Serializable
import java.util.*

data class CarId(
    val value: UUID
) : Serializable {
    constructor() : this(UUID.randomUUID())
}