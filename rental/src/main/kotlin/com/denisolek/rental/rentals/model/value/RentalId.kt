package com.denisolek.rental.rentals.model.value

import java.io.Serializable
import java.util.*

data class RentalId(
    val value: UUID
) : Serializable {
    constructor() : this(UUID.randomUUID())
}