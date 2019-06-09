package com.denisolek.fleet.cars.model.value

import javax.transaction.InvalidTransactionException

class Transmission private constructor(
    val type: TransmissionType
) {
    enum class TransmissionType {
        MANUAL,
        AUTOMATIC
    }

    companion object {
        operator fun invoke(type: String): Transmission {
            val type = when (type.toUpperCase()) {
                "MANUAL" -> TransmissionType.MANUAL
                "AUTOMATIC" -> TransmissionType.AUTOMATIC
                else -> throw InvalidTransactionException()
            }
            return Transmission(type)
        }
    }
}