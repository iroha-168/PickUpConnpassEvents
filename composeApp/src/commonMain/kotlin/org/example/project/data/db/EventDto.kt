package org.example.project.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity
data class EventDto(
    @PrimaryKey
    val id: Long,
    val title: String,
    val url: String,
    val startedAt: LocalDateTime?,
    val place: String?,
    val isFavorite: Boolean
)