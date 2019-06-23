import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object GsonConfig {
    val encoder = GsonEncoder(
        GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java, JsonDeserializer { json, type, jsonDeserializationContext ->
                    LocalDateTime.parse(json.asJsonPrimitive.asString)
                })
            .registerTypeAdapter(
                LocalDateTime::class.java, JsonSerializer<LocalDateTime> { localDateTime, type, jsonSerializationContext ->
                    JsonPrimitive(localDateTime?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                })
            .create()
    )

    val decoder = GsonDecoder(
        GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java, JsonDeserializer { json, type, jsonDeserializationContext ->
                LocalDateTime.parse(json.asJsonPrimitive.asString)
            })
            .registerTypeAdapter(
                LocalDateTime::class.java, JsonSerializer<LocalDateTime> { localDateTime, type, jsonSerializationContext ->
                    JsonPrimitive(localDateTime?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                })
            .create()
    )
}