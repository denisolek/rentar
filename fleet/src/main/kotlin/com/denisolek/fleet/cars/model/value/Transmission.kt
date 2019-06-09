package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.infrastructure.CarExceptions.InvalidTransmissionException

class Transmission private constructor(
    val type: TransmissionType
) {
    enum class TransmissionType {
        MANUAL,
        AUTOMATIC
    }

    companion object {
        operator fun invoke(value: String): Transmission {
            val type = when (value.toUpperCase()) {
                "MANUAL" -> TransmissionType.MANUAL
                "AUTOMATIC" -> TransmissionType.AUTOMATIC
                else -> throw InvalidTransmissionException()
            }
            return Transmission(type)
        }
    }
}