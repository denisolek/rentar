package com.denisolek.rental.infrastructure.gson

import com.denisolek.rental.cars.model.event.CarEvent
import com.denisolek.rental.customers.model.event.CustomerEvent
import com.denisolek.rental.infrastructure.gson.adapters.EventDeserializerAdapter
import com.denisolek.rental.infrastructure.gson.adapters.LocalDateAdapter
import com.denisolek.rental.infrastructure.gson.adapters.LocalDateTimeAdapter
import com.denisolek.rental.infrastructure.gson.adapters.LocalTimeAdapter
import com.denisolek.rental.rentals.model.event.RentalEvent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object Gson {
    fun create(): Gson {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
        builder.registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        builder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        builder.registerTypeAdapter(RentalEvent::class.java, EventDeserializerAdapter())
        builder.registerTypeAdapter(CarEvent::class.java, EventDeserializerAdapter())
        builder.registerTypeAdapter(CustomerEvent::class.java, EventDeserializerAdapter())
        return builder.create()
    }
}