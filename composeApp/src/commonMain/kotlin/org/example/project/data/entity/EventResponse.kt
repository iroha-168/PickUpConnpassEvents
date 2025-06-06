package org.example.project.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse (
    val events: List<Event>
)

@Serializable
data class Event(
    @SerialName("id")
    val id: Long,
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("started_at")
//    @Contextual
//    val startedAt: DateTime?,
    val place: String?,
)