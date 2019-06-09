package com.denisolek.fleet.infrastructure.gson

import com.denisolek.fleet.cars.model.event.CarEvent
import com.denisolek.fleet.infrastructure.gson.adapters.CarEventDeserializerAdapter
import com.denisolek.fleet.infrastructure.gson.adapters.LocalDateAdapter
import com.denisolek.fleet.infrastructure.gson.adapters.LocalDateTimeAdapter
import com.denisolek.fleet.infrastructure.gson.adapters.LocalTimeAdapter
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
        builder.registerTypeAdapter(CarEvent::class.java, CarEventDeserializerAdapter())
        return builder.create()
    }
}