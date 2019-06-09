package com.denisolek.fleet.infrastructure.gson.adapters

import com.denisolek.fleet.cars.model.event.CarAdded
import com.denisolek.fleet.infrastructure.ServiceException
import com.google.gson.*
import org.springframework.http.HttpStatus
import java.lang.reflect.Type

class CarEventDeserializerAdapter : JsonDeserializer<Any> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Any {
        val jsonObject = json.asJsonObject
        val prim = jsonObject.get("type") as JsonPrimitive
        val objectClass = when (prim.asString) {
            CarAdded::class.java.simpleName -> getObjectClass(CarAdded::class.java.name)
            else -> throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported event.")
        }
        return context.deserialize(jsonObject, objectClass)
    }

    private fun getObjectClass(className: String): Class<*> {
        try {
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e.message)
        }
    }
}