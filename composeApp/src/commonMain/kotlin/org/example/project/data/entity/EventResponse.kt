package org.example.project.data.entity

import co.touchlab.kermit.Logger
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class EventResponse (
    val events: List<Event>
)

@Serializable
data class Event(
    val id: Long,
    val title: String,
    @SerialName("started_at")
    @Serializable(with = LocalDateTimeSerializer::class)
    val startedAt: LocalDateTime?,
    val place: String?,
)

@OptIn(ExperimentalSerializationApi::class)
object LocalDateTimeSerializer : KSerializer<LocalDateTime?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime?) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): LocalDateTime? {
        try {
            val instant = Instant.parse(decoder.decodeString())
            val timeZone = TimeZone.of("Asia/Tokyo")
            return instant.toLocalDateTime(timeZone)
        } catch (e: Exception) {
            Logger.e { "HOGE: $e" }
           return null
        }
    }
}